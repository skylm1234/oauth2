package com.gejian.pixel.service.process;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.*;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommHeroUpgradeStarRequestProtobuf;
import com.gejian.pixel.proto.CommHeroUpgradeStarResponseProtobuf;
import com.gejian.pixel.proto.PlayerItemProtobuf;
import com.gejian.pixel.service.HeroService;
import com.gejian.pixel.service.LevelUpgradeService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.StarUpgradeService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author tangtao
 * @since 2021/9/7
 * @description 英雄升星
 */
@Service(CommandConstants.HERO_UPGRADE_STAR)
@Slf4j
public class HeroUpgradeStarProcessImpl implements Process<CommHeroUpgradeStarRequestProtobuf.CommHeroUpgradeStarRequest
		, CommHeroUpgradeStarResponseProtobuf.CommHeroUpgradeStarResponse> {

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private LevelUpgradeService levelUpgradeService;

	@Autowired
	private StarUpgradeService starUpgradeService;

	@Autowired
	private HeroService heroService;

	@Override
	public CommHeroUpgradeStarResponseProtobuf.CommHeroUpgradeStarResponse doProcess(
			CommHeroUpgradeStarRequestProtobuf.CommHeroUpgradeStarRequest request) throws Exception {
		Integer identifier = UserHolder.get().getIdentifier();
		CommHeroUpgradeStarResponseProtobuf.CommHeroUpgradeStarResponse.Builder result
				= CommHeroUpgradeStarResponseProtobuf.CommHeroUpgradeStarResponse.newBuilder()
				.setRequest(request).setResult(ErrorEnum.ERROR_SUCCESS);
		String hero = request.getHero();
		if (!redisTemplate.hasKey(String.format(RedisKeyConstants.USER_HERO_ATTRIBUTES, identifier, hero))) {
			return result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}
		Map<String, Object> heroMap = redisTemplate.opsForHash().entries(String.format(RedisKeyConstants.USER_HERO_ATTRIBUTES, identifier, hero));
		if (NumberUtil.parseInt(heroMap.get("level") + "") != 99) {
			return result.setResult(ErrorEnum.ERROR_HERO_LEVEL_NOT_EQUAL_99).build();
		}

		LevelUpgrade level = levelUpgradeService.get(NumberUtil.parseInt(heroMap.get("level") + ""));
		JSONObject levelJson = JSONUtil.parseObj(level);
		Long expNeed = levelJson.getLong(String.format("start%s", heroMap.get("star")));
		if (NumberUtil.parseLong(heroMap.get("exp") + "") < expNeed) {
			return result.setResult(ErrorEnum.ERROR_HERO_LEVEL_NOT_EQUAL_99).build();
		}
		int star = NumberUtil.parseInt(heroMap.get("star") + "");
		if (star == 25) {
			return result.setResult(ErrorEnum.ERROR_REACH_LIMIT).build();
		}
		if (!hero.startsWith("hero_")) {
			return result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}
		Integer id = Integer.valueOf(hero.replace("hero_", ""));

		StarUpgrade starUpgrade = starUpgradeService.get(star + 1);
		ConsumeExpand consumeExpand = JSONUtil.toBean(starUpgrade.getConsumeExpand(), ConsumeExpand.class);
		if (Helper.itemCount(redisTemplate, identifier, "gold") >= consumeExpand.getGold()) {
			Integer privateSoulChip = Helper.itemCount(redisTemplate, identifier, String.format("private_soulchip_%s", id));
			if (consumeExpand.getPrivateSoulchip() == 0 || privateSoulChip >= consumeExpand.getPrivateSoulchip()) {
				PlayerItemProtobuf.PlayerItem soulchipItem = Helper.decreaseItemValue(redisTemplate, identifier, String.format("private_soulchip_%s", id), Long.valueOf(consumeExpand.getPrivateSoulchip()));
				result.addItems(soulchipItem);
			} else {
				return result.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES).build();
			}
			PlayerItemProtobuf.PlayerItem goldItem = Helper.decreaseItemValue(redisTemplate, identifier, "gold", Long.valueOf(consumeExpand.getGold()));
			result.addItems(goldItem);
			heroMap.put("level", 1);
			star += 1;
			heroMap.put("star", star);
			switch (star) {
				case 6:
					break;
				case 11:
					break;
				case 16:
					break;
				case 21:
					break;
				default:
					break;
			}
			heroMap.put("exp", 0L);
			String type = (String) heroMap.get("type");
			if (!type.startsWith("hero_")) {
				return result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
			}
			id = Integer.valueOf(type.replace("hero_", ""));
			Hero heroHash = heroService.getHash().get(id);
			if (Objects.isNull(heroHash)) {
				return result.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
			}
			JSONObject starUpgradeJson = JSONUtil.parseObj(heroHash.getStarUpgradeFomula());
			Star starAttribute = starUpgradeJson.getBean(String.format("star_%s", star), Star.class);
			heroMap.put("hp", NumberUtil.parseInt(heroMap.get("hp") + "") + starAttribute.getHp());
			heroMap.put("attack", NumberUtil.parseInt(heroMap.get("attack") + "") + starAttribute.getAttack());
			heroMap.put("def", NumberUtil.parseInt(heroMap.get("def") + "") + starAttribute.getDefense());
			heroMap.put("speed", NumberUtil.parseInt(heroMap.get("speed") + "") + starAttribute.getSpeed());
			heroMap.put("grow_hp", starAttribute.getHpUpgrade());
			heroMap.put("grow_attack", starAttribute.getAttackUpgrade());
			heroMap.put("grow_def", starAttribute.getDefenseUpgrade());
			heroMap.put("grow_speed", starAttribute.getSpeedUpgrade());
			int power = (NumberUtil.parseInt(heroMap.get("hp") + "") + NumberUtil.parseInt(heroMap.get("attack") + "") + NumberUtil.parseInt(heroMap.get("def") + "") + NumberUtil.parseInt(heroMap.get("speed") + ""));
			redisTemplate.opsForHash().put(String.format(RedisKeyConstants.USER_HEROS, identifier), type, power);
			redisTemplate.opsForHash().putAll(String.format(RedisKeyConstants.USER_HERO_ATTRIBUTES, identifier, type), heroMap);
			Helper.updateRanklistPower(redisTemplate, identifier);
			Hero heroNow = heroService.getHash().get(id);
			String desc = "";
			if (star <= 5) {
				desc = "<color=white>白色" + star + "星</color>";
			} else if (star <= 10) {
				desc = "<color=white>绿色" + (star - 5) + "星</color>";
			} else if (star <= 15) {
				desc = "<color=white>蓝色" + (star - 10) + "星</color>";
			} else if (star <= 20) {
				desc = "<color=white>紫色" + (star - 15) + "星</color>";
			} else {
				desc = "<color=white>金色" + (star - 20) + "星</color>";
			}
			String nickname = Helper.stringValue(redisTemplate, identifier, "nickname");
			if (StrUtil.isNotBlank(nickname) && star >= 6) {
				Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + nickname + "</color>升级英雄<color=red>" + heroNow.getName() + "</color>到" + desc + "！");
			}
		} else {
			return result.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES).build();
		}
		return result.build();
	}
}
