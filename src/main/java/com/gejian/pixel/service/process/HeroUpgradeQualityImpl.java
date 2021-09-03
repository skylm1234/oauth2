package com.gejian.pixel.service.process;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gejian.pixel.annotation.CommandResponse;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.CommHeroUpgradeQualityRequestProtobuf;
import com.gejian.pixel.proto.CommHeroUpgradeQualityResponseProtobuf;
import com.gejian.pixel.proto.HeroBasicInfoProtobuf;
import com.gejian.pixel.proto.HeroSkillProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.ChannelHolder;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.ToUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	private final static String qualityStr = "quality";

	private final static Integer number = 4;

	private final static Integer qualityNoMoreThanNumber = 5;

	@Override
	public CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse
	doProcess(CommHeroUpgradeQualityRequestProtobuf.CommHeroUpgradeQualityRequest commHeroUpgradeQualityRequest) throws Exception {

		CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse.Builder reply = CommHeroUpgradeQualityResponseProtobuf.CommHeroUpgradeQualityResponse.newBuilder();
		reply.setRequest(commHeroUpgradeQualityRequest);

		//获取当前channel
		Channel channel = ChannelHolder.get();
		//TODO 不晓得是啥
		Integer identifier = 123;

		String hero = commHeroUpgradeQualityRequest.getHero();

		//当前方法名
		String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();

		Formatter formatter = new Formatter();
		String redisKey = formatter.format("u:%d:%s:attributes", identifier, hero).toString();
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
			RubyConstQualityUpgradeTable item = getRubyConstQualityUpgradeTable(qualityValue);
			if (Helper.itemCount(redisTemplate, identifier, "private_soulchip_" + id) >= item.getConsumeExpand().getPrivateSoulchip()) {
				//减少物品个数
				Helper.decreaseItemValue(redisTemplate, identifier, "private_soulchip_" + id, item.getConsumeExpand().getPrivateSoulchip());
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
				float rate0 = quality > 2 ? Float.parseFloat(getRubyConstQualityUpgradeRateTable(quality.intValue() - 2).getUp()) : 0f;
				//获取rate
				float rate = Float.parseFloat(getRubyConstQualityUpgradeRateTable(quality.intValue() - 1).getUp());
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
				redisTemplate.opsForHash().put(formatter.format("u:%d:heros", identifier).toString(), heroMap.get("type"), power);
				//存个map
				redisTemplate.opsForHash().putAll(formatter.format("u:%d:%s:attributes", identifier, heroMap.get("type")).toString(), heroMap);

				Helper.updateRanklistPower(redisTemplate, identifier, channel);

				Map<Object, Object> newHeroMap = redisTemplate.opsForHash().entries(redisKey);
				HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBasicInfo = JSON.parseObject(JSONObject.toJSONString(newHeroMap), HeroBasicInfoProtobuf.HeroBasicInfo.Builder.class);

				heroBasicInfo.setNumber(1);

				Map<Object, Object> skillsMap = redisTemplate.opsForHash().entries(formatter.format("u:%d:%s:skills", identifier, hero));
				Set<Object> skillKey = skillsMap.keySet();
				for (Object o : skillKey) {
					HeroSkillProtobuf.HeroSkill.Builder skill = HeroSkillProtobuf.HeroSkill.newBuilder();
					skill.setType(String.valueOf(o));
					skill.setLevel((Integer) skillsMap.get(o));
					heroBasicInfo.addSkills(skill);
				}
				reply.addHeros(heroBasicInfo);

				if (Helper.stringValue(redisTemplate, identifier, "nickname") != null) {

					RubyConstHeroTableHash rubyConstHeroTableHash = getRubyConstHeroTableHash();

					List<String> qarray = new ArrayList<>();
					qarray.add("<color=blue>蓝色品质</color>");
					qarray.add("<color=purple>紫色品质</color>");
					qarray.add("<color=gold>金色品质</color>");

					String nickName = new String(Helper.stringValue(redisTemplate, identifier, "nickname").getBytes("UTF-8"), "UTF-8");
					Helper.boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" +
							nickName + "</color>升级英雄<color=red>" + rubyConstHeroTableHash.getName() +
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


	@Data
	class RubyConstQualityUpgradeTable {
		private Integer id;
		private Integer quality;
		private String desc;
		private ConsumeExpand consumeExpand;
	}

	@Data
	class ConsumeExpand {
		private Long privateSoulchip;
		private String consume;
	}

	/**
	 * 获取RubyConstQualityUpgradeTable对象
	 *
	 * @return RubyConstQualityUpgradeRateTable
	 */
	private RubyConstQualityUpgradeTable getRubyConstQualityUpgradeTable(Integer id) {
		//TODO 返回一个JSON数组
		return new RubyConstQualityUpgradeTable();
	}

	@Data
	class RubyConstQualityUpgradeRateTable {
		private Integer id;
		private Integer quality;
		private String desc;
		private String up;
	}

	/**
	 * 获取RubyConstQualityUpgradeRateTable对象
	 *
	 * @return RubyConstQualityUpgradeRateTable
	 */
	private RubyConstQualityUpgradeRateTable getRubyConstQualityUpgradeRateTable(Integer id) {
		//TODO 返回一个JSON数组
		return new RubyConstQualityUpgradeRateTable();
	}

	@Data
	class RubyConstHeroTableHash {
		private Integer id;
		private String name;
		private Integer type;
		private String desc;
		private Integer color;
		private BasicExpand basicExpand;
		private String basic;
		private BasicUpgradeExpand basicUpgradeExpand;
		private String basicUpgrade;
		private String skill;
		private Map<String, Start> starUpgradeFomula;
		private String starUpgrade;
	}

	@Data
	class BasicExpand {
		private Integer hp;
		private Integer attack;
		private Integer defense;
		private Integer speed;
	}

	@Data
	class BasicUpgradeExpand {
		Integer hp;
		Integer attack;
		Integer defense;
		Integer speed;
	}

	@Data
	class Start {
		Integer hp;
		Integer attack;
		Integer defense;
		Integer speed;
		Integer hpUpgrade;
		Integer attackUpgrade;
		Integer defenseUpgrade;
		Integer speedUpgrade;
	}

	/**
	 * 获取RubyConstSkillTableHash
	 *
	 * @return
	 */
	private RubyConstHeroTableHash getRubyConstHeroTableHash() {
		return new RubyConstHeroTableHash();
	}

}
