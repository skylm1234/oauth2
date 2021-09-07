package com.gejian.pixel.service.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.*;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.HeroService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.service.QualityUpgradeRateService;
import com.gejian.pixel.service.QualityUpgradeService;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.ToUtil;
import com.gejian.pixel.utils.UserHolder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service(CommandConstants.HERO_UPGRADE_QUALITY)
@CommandResponse(CommandConstants.HERO_UPGRADE_QUALITY)
@Slf4j
public class HeroUpgradeQualityImpl implements Process<CommHeroUpgradeQualityRequestProtobuf.CommHeroUpgradeQualityRequest
		, CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse> {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private QualityUpgradeService qualityUpgradeService;
	@Autowired
	private QualityUpgradeRateService qualityUpgradeRateService;
	@Autowired
	private HeroService heroService;


	private final static String qualityStr = "quality";

	private final static Integer number = 4;

	private final static Integer qualityNoMoreThanNumber = 5;


	@Override
	public CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse
	doProcess(CommHeroUpgradeQualityRequestProtobuf.CommHeroUpgradeQualityRequest commHeroUpgradeQualityRequest) throws Exception {

		CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse.Builder reply = CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse.newBuilder();
		reply.setRequest(commHeroUpgradeQualityRequest);

		UserInfo userInfo = UserHolder.get();
		Integer identifier = userInfo.getIdentifier();

		String hero = commHeroUpgradeQualityRequest.getHero();

		//当前方法名
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		String redisKey = String.format("u:%d:%s:attributes", identifier, hero);
		if (!redisTemplate.hasKey(redisKey)) {
			log.error("FAILED: {}=>{}:{}", identifier, methodName, Thread.currentThread().getStackTrace()[1].getLineNumber());
			return reply.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}

		Map<Object, Object> heroMap = redisTemplate.opsForHash().entries(redisKey);
		Set<Object> set = heroMap.keySet();
		for (Object o : set) {
			if (!"type".equals(o)) {
				//value转成Integer
				heroMap.put(o, ToUtil.to_i(heroMap.get(o)));
			}
		}

		if ((Integer) heroMap.get(qualityStr) >= number) {
			log.error("FAILED: {}=>{}:{}", identifier, methodName, Thread.currentThread().getStackTrace()[1].getLineNumber());
			return reply.setResult(ErrorEnum.ERROR_REACH_QUALITY_LIMIT).build();
		}

		String pattern = "^hero_(\\d+)$";
		Pattern compile = Pattern.compile(pattern);
		Matcher m = compile.matcher(hero);
		if (m.find()) {
			Integer id = ToUtil.to_i(m.group(1));
			Integer qualityValue = ToUtil.to_i(heroMap.get(qualityStr)) - 1;

			//获取RubyConstQualityUpgradeTable
			ConstQualityUpgradeTableItemExProtobuf.ConstQualityUpgradeTableItemEx item = qualityUpgradeService.getQualityUpgradeById(qualityValue);

			if (Helper.itemCount(redisTemplate, identifier, "private_soulchip_" + id) >= item.getConsumeExpand().getPrivateSoulchip()) {
				//减少物品个数
				Helper.decreaseItemValue(redisTemplate, identifier, "private_soulchip_" + id, (long) item.getConsumeExpand().getPrivateSoulchip());
				//自增加一
				Long quality = redisTemplate.opsForHash().increment(redisKey, "quality", 1);
				if (quality >= qualityNoMoreThanNumber) {
					//如果大于等于5，就又自减一。在打个错误日志
					redisTemplate.opsForHash().increment(redisKey, "quality", -1);
					log.error("FAILED: {}=>{}:{}", identifier, methodName, Thread.currentThread().getStackTrace()[1].getLineNumber());
					return reply.setResult(ErrorEnum.ERROR_REACH_QUALITY_LIMIT).build();
				}

				//赋值
				heroMap.put(qualityStr, quality);

				//获取rate0
				QualityUpgradeRate qualityUpgradeRate0 = qualityUpgradeRateService.getById(quality.intValue() - 2);
				float rate0 = quality > 2 ? Float.parseFloat(qualityUpgradeRate0.getUp()) : 0f;
				//获取rate
				QualityUpgradeRate qualityUpgradeRate = qualityUpgradeRateService.getById(quality.intValue() - 1);
				float rate = Float.parseFloat(qualityUpgradeRate.getUp());
				log.info(heroMap.toString());

				//map赋值
				heroMap.put("hp", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("hp") * rate)) : ToUtil.to_i((float) heroMap.get("hp") / rate0 * rate));
				heroMap.put("attack", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("attack") * rate)) : ToUtil.to_i((float) heroMap.get("attack") / rate0 * rate));
				heroMap.put("def", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("def") * rate)) : ToUtil.to_i((float) heroMap.get("def") / rate0 * rate));
				heroMap.put("speed", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("speed") * rate)) : ToUtil.to_i((float) heroMap.get("speed") / rate0 * rate));
				heroMap.put("grow_hp", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("grow_hp") * rate)) : ToUtil.to_i((float) heroMap.get("grow_hp") / rate0 * rate));
				heroMap.put("grow_attack", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("grow_attack") * rate)) : ToUtil.to_i((float) heroMap.get("grow_attack") / rate0 * rate));
				heroMap.put("grow_def", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("grow_def") * rate)) : ToUtil.to_i((float) heroMap.get("grow_def") / rate0 * rate));
				heroMap.put("grow_speed", (quality == 2) ? (ToUtil.to_i((float) heroMap.get("grow_speed") * rate)) : ToUtil.to_i((float) heroMap.get("grow_speed") / rate0 * rate));

				String power = heroMap.get("hp").toString() + heroMap.get("def").toString() + heroMap.get("attack").toString() + heroMap.get("speed").toString();
				//设置power
				redisTemplate.opsForHash().put(String.format("u:%d:heros", identifier), heroMap.get("type"), power);
				//存个map
				redisTemplate.opsForHash().putAll(String.format("u:%d:%s:attributes", identifier, heroMap.get("type")), heroMap);

				PlayerItemProtobuf.PlayerItem playerItem = Helper.updateRanklistPower(redisTemplate, identifier);
				if (Objects.nonNull(playerItem)) {
					reply.addItems(playerItem);
				}

				Map<Object, Object> newHeroMap = redisTemplate.opsForHash().entries(redisKey);
				HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfo = JSON.parseObject(JSONObject.toJSONString(newHeroMap), HeroBasicInfoProtobuf.HeroBasicInfo.Builder.class);

				heroBasicInfo.setNumber(1);

				Map<Object, Object> skillsMap = redisTemplate.opsForHash().entries(String.format("u:%d:%s:skills", identifier, hero));
				Set<Object> skillKey = skillsMap.keySet();
				for (Object o : skillKey) {
					HeroSkillProtobuf.HeroSkill.Builder skill = HeroSkillProtobuf.HeroSkill.newBuilder();
					skill.setType(String.valueOf(o));
					skill.setLevel((Integer) skillsMap.get(o));
					heroBasicInfo.addSkills(skill);
				}
				reply.addHeros(heroBasicInfo);

				if (Helper.stringValue(redisTemplate, identifier, "nickname") != null) {

					Hero heroTable = heroService.getById(id);

					List<String> qarray = new ArrayList<>();
					qarray.add("<color=blue>蓝色品质</color>");
					qarray.add("<color=purple>紫色品质</color>");
					qarray.add("<color=gold>金色品质</color>");

					String nickName = new String(Helper.stringValue(redisTemplate, identifier, "nickname").getBytes("UTF-8"), "UTF-8");
					Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" +
							nickName + "</color>升级英雄<color=red>" + heroTable.getName() +
							"</color>到" + qarray.get(qualityValue - 2) + "!");
				}
			} else {
				log.error("{}:{}", Helper.itemCount(redisTemplate, identifier, "private_soulchip_" + id), item.getConsumeExpand().getPrivateSoulchip());
				log.error("FAILED: {}=>{}:{}", identifier, methodName, Thread.currentThread().getStackTrace()[1].getLineNumber());
				return reply.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
			}

		} else {
			log.error("FAILED: {}=>{}:{}", identifier, methodName, Thread.currentThread().getStackTrace()[1].getLineNumber());
			return reply.setResult(ErrorEnum.ERROR_HERO_NOT_FOUND).build();
		}


		return reply.build();
	}
}
