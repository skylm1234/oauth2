package com.gejian.pixel.service.process;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.ConsumeExpBookRedisKeyConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.LevelUpgrade;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.ExpBookService;
import com.gejian.pixel.service.LevelUpgradeService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Hyb
 * @date : 2021/9/3 15:22
 * @description:
 */
@Service(CommandConstants.LET_HERO_CONSUME_EXP_BOOK)
@Slf4j
public class LetHeroConsumeExpBookProcessImpl implements Process<CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest
		, CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private ExpBookService expBookService;

	@Autowired
	private LevelUpgradeService levelUpgradeService;

	private static final String TYPE = "type";

	private static final String CONSUMEEXPBOOKS = "consumeexpbooks";

	private static final String DAILY_EXP_BOOK_CONSUME = "daily_exp_book_consume";

	@Override
	public CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse doProcess(CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest request) throws Exception {

		CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse.Builder response = CommLetHeroConsumeExpBookResponseProtobuf.CommLetHeroConsumeExpBookResponse.newBuilder();

		// 用户身份ID
		Integer identifier = UserHolder.get().getIdentifier();
		String hero = request.getHero();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("d", String.valueOf(identifier));
		map.put("s", hero);

		List<String> strings = Arrays.asList(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_ATTRIBUTES, map));

		if (redisTemplate.countExistingKeys(strings).longValue() == 0) {
			return response.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}

		boolean dirty = false;

		Map<String, Object> hh = redisTemplate.opsForHash().entries(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_ATTRIBUTES, map));

		HashMap<String, Object> heroMap = new HashMap<String, Object>();

		hh.entrySet().forEach(entry -> {
			heroMap.put(entry.getKey(), ObjectUtil.equal(entry.getKey(), TYPE) ? entry.getValue() :
					Integer.valueOf((String) entry.getValue()));
		});

		response.addArchives(Helper.onNotifyEventOfPromotions(redisTemplate, CONSUMEEXPBOOKS, request.getBooksCount()
				, identifier));

		response.addArchives(Helper.onNotifyEventOfPromotions(redisTemplate, DAILY_EXP_BOOK_CONSUME, request.getBooksCount()
				, identifier));

		for (int i = 0; i < request.getBooksCount(); i++) {

			if (null != Helper.decreaseItemValue(redisTemplate,
					identifier, request.getBooks(i), 1L)) {
				Integer delta = expBookService.getExpBook(request.getBooks(i)).getValue();
				if (null == delta) {
					return response.setResult(ErrorEnum.ERROR_EXP_BOOK_NOT_EXIST).build();
				}

				dirty = true;

				int exp = (int) heroMap.get("exp");

				exp += delta;
				heroMap.put("exp", exp);

				while ((int) heroMap.get("level") < 99) {
					LevelUpgrade item = levelUpgradeService.get((int) heroMap.get("level") + 1);
					Integer expNeed = ReflectUtil.invoke(item, "getStart" + (int) heroMap.get("star"));
					if ((int) heroMap.get("exp") >= expNeed) {
						heroMap.put("exp", (int) heroMap.get("exp") - expNeed);
						heroMap.put("level", (int) heroMap.get("level") + 1);
						heroMap.put("hp", (int) heroMap.get("hp") + (int) heroMap.get("grow_hp"));
						heroMap.put("def", (int) heroMap.get("def") + (int) heroMap.get("grow_def"));
						heroMap.put("attack", (int) heroMap.get("attack") + (int) heroMap.get("grow_attack"));
						heroMap.put("speed", (int) heroMap.get("speed") + (int) heroMap.get("grow_speed"));
						if (NumberUtil.parseInt(heroMap.get("level") + "") == 99) {
							heroMap.put("exp", expNeed);
						}
					} else {
						break;
					}
				}
			} else {
				return response.setResult(ErrorEnum.ERROR_EXP_BOOK_NOT_EXIST).build();
			}
		}

		if (dirty) {
			int power = (int) heroMap.get("hp") + (int) heroMap.get("def") + (int) heroMap.get("attack")
					+ (int) heroMap.get("speed");
			redisTemplate.opsForHash().put(StrUtil.format(RedisKeyConstants.USER_HEARO, identifier)
					, hero, power);
			PlayerItemProtobuf.PlayerItem playerItem = Helper.updateRanklistPower(redisTemplate, identifier);
			response.addItems(playerItem);

			redisTemplate.opsForHash().putAll(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_ATTRIBUTES, map), heroMap);

			HeroBasicInfoProtobuf.HeroBasicInfo.Builder h = ((HeroBasicInfoProtobuf.HeroBasicInfo) toProtoBean(HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder(), JSONUtil.toJsonStr(heroMap))).toBuilder();

			Map<String, Object> skills = redisTemplate.opsForHash().entries(StrUtil.format(ConsumeExpBookRedisKeyConstants.USER_SKILLS, map));

			skills.entrySet().forEach(objectObjectEntry -> {

				HeroSkillProtobuf.HeroSkill s = HeroSkillProtobuf.HeroSkill.newBuilder()
						.setType(objectObjectEntry.getKey())
						.setLevel(NumberUtil.parseInt(objectObjectEntry.getValue().toString()))
						.build();
				h.addSkills(s);
			});

			response.addHeros(h);
		}

		return response.build();
	}

	private static Message toProtoBean(Message.Builder targetBuilder, String json) throws IOException {
		JsonFormat.parser().merge(json, targetBuilder);
		return targetBuilder.build();
	}
}
