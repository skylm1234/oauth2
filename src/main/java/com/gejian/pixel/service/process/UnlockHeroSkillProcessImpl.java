package com.gejian.pixel.service.process;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.ext.SkillUpgradeDO;
import com.gejian.pixel.proto.CommUnlockHeroSkillRequestProtobuf;
import com.gejian.pixel.proto.CommUnlockHeroSkillResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.SkillService;
import com.gejian.pixel.utils.ChannelHolder;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.bouncycastle.util.test.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author tangtao
 * @since 2021/8/31
 */
@Service(CommandConstants.UNLOCK_HERO_SKILL)
@Slf4j
public class UnlockHeroSkillProcessImpl implements Process<CommUnlockHeroSkillRequestProtobuf.CommUnlockHeroSkillRequest
		, CommUnlockHeroSkillResponseProtobuf.CommUnlockHeroSkillResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private SkillService skillService;

	@Override
	public CommUnlockHeroSkillResponseProtobuf.CommUnlockHeroSkillResponse doProcess(
			CommUnlockHeroSkillRequestProtobuf.CommUnlockHeroSkillRequest request)
			throws Exception {

		/*CommUnlockHeroSkillRequestProtobuf.CommUnlockHeroSkillRequest skillRequest = CommUnlockHeroSkillRequestProtobuf.
				CommUnlockHeroSkillRequest.newBuilder().build();*/

		CommUnlockHeroSkillResponseProtobuf.CommUnlockHeroSkillResponse.Builder result = CommUnlockHeroSkillResponseProtobuf.CommUnlockHeroSkillResponse.newBuilder()
				.setRequest(request);
		Integer identifier = UserHolder.get().getIdentifier();

		String hero = request.getHero();
		String skill = request.getSkill();
		String heroKey = String.format("u:%s:%s:attributes", identifier, hero);
		if (!redisTemplate.hasKey(heroKey)) {
			return result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}
		String skillKey = String.format("u:%s:%s:skills", identifier, hero);

		Map<String, Object> skillsMap = redisTemplate.opsForHash().entries(skillKey);
		if (!skillsMap.containsKey(skill)) {
			return result.setResult(ErrorEnum.ERROR_SKILL_NOT_FOUND).build();
		}
		if (NumberUtil.parseInt(skillsMap.get(skill)+"") > 0) {
			return result.setResult(ErrorEnum.ERROR_SKILL_ALREADY_UNLOCK).build();
		}
		//todo 获取升级技能所需要的技能书以及金币
		String activeConsumeFomula = skillService.getById(skill).getActiveConsumeFomula();
		//SkillUpgradeDO upgradeDO = getSkillUpgradeDO(skillsMap);
		JSONObject jsonObject = JSONUtil.parseObj(activeConsumeFomula);

		if (Helper.itemCount(redisTemplate, identifier, String.format("book_%s", skill)) < jsonObject.getLong("book")
				|| Helper.itemCount(redisTemplate, identifier, "gold") < jsonObject.getLong("gold")) {
			return result.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES).build();
		}

		//减少金币以及技能书数量
		PlayerItemProtobuf.PlayerItem gold = Helper.decreaseItemValue(redisTemplate, identifier, "gold", jsonObject.getLong("gold"));

		PlayerItemProtobuf.PlayerItem playerItem = Helper.decreaseItemValue(redisTemplate, identifier, String.format("book_%s", skill), jsonObject.getLong("book"));
		redisTemplate.opsForHash().increment(String.format("u:%s:%s:skills", identifier, hero), skill, 1);

		return result.setRequest(request)
				.setResult(ErrorEnum.ERROR_SUCCESS)
				.addItems(gold)
				.addItems(playerItem)
				.build();
	}

	private SkillUpgradeDO getSkillUpgradeDO(Map<String, Object> skillsMap) {
		return new SkillUpgradeDO();
	}


	public static void main(String[] args) {
		String fileUrl = "E:\\skilljson.txt";
		List<String> lines = FileUtil.readLines(fileUrl, StandardCharsets.UTF_8);
		String skillInsertSql = "INSERT INTO skill(%s) values (%s);";
		String upgradeInsertSql = "INSERT INTO skill_upgrade(%s) values (%s);";
		List<String> sqlList = Lists.newArrayList();
		lines.forEach(line -> {
			List<String> skillColumnList = Lists.newArrayList();
			List<String> skillValueList = Lists.newArrayList();

			JSONObject jsonObject = JSONUtil.parseObj(line);
			Set<String> keySet = jsonObject.keySet();
			for (String key : keySet) {
				if (key.equals("active_consume_fomula")) {
					JSONObject basic = jsonObject.getJSONObject(key);
					Set<String> basicKeys = basic.keySet();
					for (String basicKey : basicKeys) {
						skillColumnList.add(basicKey);
						skillValueList.add("'" + basic.get(basicKey) + "'");
					}
					continue;
				}
				if (key.equals("desc")) {
					skillColumnList.add("skill_desc");
					skillValueList.add("'" + jsonObject.get(key) + "'");
					continue;
				}
				if (key.equals("upgrade_consume_fomula")) {
					JSONObject starJsonList = jsonObject.getJSONObject(key);
					for (int i = 1; i < 10; i++) {
						List<String> starColumnList = Lists.newArrayList();
						List<String> starValueList = Lists.newArrayList();
						JSONObject starJson = starJsonList.getJSONObject("X" + i);
						if (Objects.isNull(starJson)) {
							continue;
						}
						Set<String> starKeys = starJson.keySet();
						for (String starKey : starKeys) {
							starColumnList.add(starKey);
							starValueList.add("'" + starJson.get(starKey) + "'");
						}
						starColumnList.add("level");
						starValueList.add("'X" + i + "'");
						starColumnList.add("skill_id");
						starValueList.add("'" + jsonObject.getStr("id") + "'");
						sqlList.add(String.format(upgradeInsertSql, String.join(",", starColumnList)
								, String.join(",", starValueList)));
					}
					continue;
				}
				skillColumnList.add(key);
				skillValueList.add("'" + jsonObject.get(key) + "'");
			}
			sqlList.add(String.format(skillInsertSql, String.join(",", skillColumnList), String.join(",", skillValueList)));
		});
		FileUtil.appendLines(sqlList, "E:\\sql.txt", StandardCharsets.UTF_8);

	}

}
