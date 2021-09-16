package com.gejian.pixel.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.*;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.entity.*;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.*;
import com.gejian.pixel.service.impl.HeroServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author ljb
 * @date 2021年08月30日 10:29
 * @description 公共工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Helper {

	private final HeroService heroService;
	private final QualityUpgradeRateService qualityUpgradeRateService;
	private final NewStoreHotService newStoreHotService;
	private final NewStoreDiscountService newStoreDiscountService;
	private final NewStoreTimeLimitService newStoreTimeLimitService;

	static List<Hero> heroList = null;
	static List<QualityUpgradeRate> qualityUpgradeRates = null;
	static Map<Integer, Hero> heroHashList = null;
	static List<NewStoreHot> newStoreHots = null;
	static List<NewStoreDiscount> newStoreDiscounts = null;
	static List<NewStoreTimeLimit> newStoreTimeLimits = null;

	private JSONArray skillToHero = new JSONArray();

	private void heroNameToSkillBookHash() {
		//需要修改常量数据获取
		//JSONArray rubyConstHeroTable = generated.getRUBY_CONST_HERO_TABLE();
		List<Hero> heroList = heroService.list();
		if (heroList != null) {
			for (Hero constHero : heroList) {
				JSONObject hero = new JSONObject();
				hero.putOnce(constHero.getSkillA(), constHero.getName());
				hero.putOnce(constHero.getSkill1(), constHero.getName());
				hero.putOnce(constHero.getSkill2(), constHero.getName());
				hero.putOnce(constHero.getSkill3(), constHero.getName());
				hero.putOnce(constHero.getSkill4(), constHero.getName());
				skillToHero.add(hero);
			}
		}
	}

	@PostConstruct
	public void init() {
		heroNameToSkillBookHash();
		heroList = heroService.list();
		qualityUpgradeRates = qualityUpgradeRateService.list();
		heroHashList = heroService.getHash();
		newStoreHots = newStoreHotService.list();
		newStoreDiscounts = newStoreDiscountService.list();
		newStoreTimeLimits = newStoreTimeLimitService.list();
	}

	/**
	 * 字符串转换成十六进制字符串
	 *
	 * @param s 需要转换的字符串
	 * @return 十六进制字符串
	 */
	public static String hexEncode(String s) {
		return HexUtil.encodeHexStr(s.getBytes());
	}

	/**
	 * 将十六进制字符数组转换为字符串，默认编码UTF-8
	 *
	 * @param hexStr 十六进制字符串
	 * @return 字符串
	 */
	public static String hexDecode(String hexStr) {
		return HexUtil.decodeHexStr(hexStr);
	}

	/**
	 * 生成礼物背包标识符
	 *
	 * @param redisTemplate redis
	 * @return 背包标识符
	 */
	public static String giftbagIdentifier(RedisTemplate redisTemplate) {
		return StrFormatter.format("giftbag_identifier_{}", redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
	}

	/**
	 * 生成用户标识符
	 *
	 * @param redisTemplate redis
	 * @return 用户标识符
	 */
	public static String generateUserIdentifier(RedisTemplate redisTemplate) {
		return redisTemplate.opsForValue().increment("user:max:player_identifier") + "";
	}

	/**
	 * 生成英雄标识符
	 *
	 * @param redisTemplate redis
	 * @return 英雄标识符
	 */
	public static String generateHeroIdentifier(RedisTemplate redisTemplate) {
		return redisTemplate.opsForValue().increment("user:max:hero_identifier") + "";
	}

	/**
	 * 生成IAP标识符
	 *
	 * @param redisTemplate redis
	 * @return IAP标识符
	 */
	public static String generateIapIdentifier(RedisTemplate redisTemplate) {
		return redisTemplate.opsForValue().increment("user:max:iap_identifier") + "";
	}

	/**
	 * 得到字符串值
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @return
	 */
	public static String stringValue(RedisTemplate redisTemplate, Integer identifier, String name) {
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":strings", name);
		if (result != null) {
			return hexDecode(result.toString());
		}
		return null;
	}

	/**
	 * 得到字符串值
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @param value         值
	 * @return
	 */
	public static PlayerStringProtobuf.PlayerString setStringValue(RedisTemplate redisTemplate
			, Integer identifier
			, String name
			, String value) {
		redisTemplate.opsForHash().put("u:" + identifier + ":strings", name, hexEncode(value));
		PlayerStringProtobuf.PlayerString item = PlayerStringProtobuf.PlayerString
				.newBuilder()
				.setKey(name)
				.setValue(value)
				.build();
		return item;
	}

	/**
	 * 获得物品个数
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @return
	 */
	public static Integer itemCount(RedisTemplate redisTemplate, Integer identifier, String name) {
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":items", name);
		if (result != null) {
			return Integer.valueOf(result + "");
		}
		return 0;
	}


	/**
	 * 设置物品个数
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @param delta         值
	 * @return 物品信息
	 */
	public static PlayerItemProtobuf.PlayerItem setItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta) {
		redisTemplate.opsForHash().put("u:" + identifier + ":items", name, String.valueOf(delta));
		PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
				.newBuilder()
				.setKey(name)
				.setValue(delta)
				.build();
		return item;
	}

	/**
	 * 增加物品个数
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @param delta         值
	 * @return
	 */
	public static PlayerItemProtobuf.PlayerItem increaseItemValue(RedisTemplate redisTemplate, Integer identifier, String name, Long delta) {
		PlayerItemProtobuf.PlayerItem.Builder builder = PlayerItemProtobuf.PlayerItem
				.newBuilder()
				.setKey(name);
		Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
		long current = Long.parseLong(result + "");
		if (delta <= 0) {
			if (current < 0) {
				Long increment = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta * -1);
				builder.setValue(increment);
				return null;
			}
		}
		builder.setValue(current);
		return builder.build();
	}

	/**
	 * 减少物品个数
	 *
	 * @param redisTemplate redis
	 * @param identifier    标识符
	 * @param name          名称
	 * @param delta         值
	 * @return
	 */
	public static PlayerItemProtobuf.PlayerItem decreaseItemValue(RedisTemplate redisTemplate, Integer identifier, String name, Long delta) {
		return increaseItemValue(redisTemplate, identifier, name, -1*delta);
	}

	/**
	 * @param ar 数组
	 * @return
	 */
	public static Integer selectFromMultipleAward(Integer[][] ar) {
		for (int i = 0; i < ar.length; i++) {
			if (i != 0) {
				ar[i][1] = ar[i][1] + ar[i - 1][1];
			}
		}
		Integer factor = 0;
		if (ar[ar.length - 1][1] < 100) {
			factor = RandomUtil.randomInt(100);
		} else {
			factor = RandomUtil.randomInt(ar[ar.length - 1][1]);
		}
		for (int i = 0; i < ar.length; i++) {
			if (factor <= ar[i][1]) {
				return i;
			}
		}
		return null;
	}

	/**
	 * @param ar Object集合
	 * @return
	 */
	public static Integer selectFromMultipleAward(List<Object> ar) {

		for (int i = 0; i < ar.size(); i++) {
			if (i != 0) {
				Integer[] temp = (Integer[]) ar.get(i);
				temp[1] = temp[1] + temp[i - 1];
			}
		}
		Integer factor = 0;
		Integer[] temp2 = (Integer[]) ar.get(ar.size() - 1);
		if (temp2[1] < 100) {
			factor = RandomUtil.randomInt(100);
		} else {
			factor = RandomUtil.randomInt(temp2[1]);
		}
		for (int i = 0; i < ar.size(); i++) {
			Integer[] temp3 = (Integer[]) ar.get(i);
			if (factor <= temp3[1]) {
				return i;
			}
		}
		return null;
	}

	/**
	 * 世界广播时间
	 *
	 * @param desc 内容
	 */
	public static void boardcaseWorldEvent(String desc) {
		log.info("{} boardcast_world_event, {}", DateUtil.now(), desc);
		CommWorldEventUpdateProtobuf.CommWorldEventUpdate event = CommWorldEventUpdateProtobuf.CommWorldEventUpdate
				.newBuilder()
				.setType(4)
				.setInfo(desc)
				.setFoo("\\0\\0\\0\\0")
				.setBar("\\0\\0\\0")
				.setBee(1)
				.build();

		MessageBaseProtobuf.MessageBase base = MessageBaseProtobuf.MessageBase
				.newBuilder()
				.setName("COMM_WORLD_EVENT_UPDATE")
				.setData(event.toByteString())
				.build();
		BroadcastUtil.broadcast(base);
	}

	public static PlayerInfoProtobuf.PlayerInfo awardHeroForMe(RedisTemplate redisTemplate, Integer identifier, String type, String parameter) {
		boolean r = ReUtil.isMatch("^hero_(\\d+)$", type);
		PlayerInfoProtobuf.PlayerInfo.Builder playerInfo = PlayerInfoProtobuf.PlayerInfo.newBuilder();
		if (r) {
			String id = type.split("_")[1];

			Hero record = heroHashList.get(NumberUtil.parseInt(id));

			if (redisTemplate.opsForHash().putIfAbsent("u:" + identifier + ":heros", type, "1")) {
				PlayerItemProtobuf.PlayerItem item = onNotifyEventOfPromotions(redisTemplate, "mostheros", 1, identifier);
				playerInfo.addArchives(item);
				HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBuilder = HeroBasicInfoProtobuf.HeroBasicInfo
						.newBuilder()
						.setId(Integer.parseInt(generateHeroIdentifier(redisTemplate)))
						.setType(type)
						.setLevel(1)    // 抽出来都是1级
						.setExp(0)        // 无经验
						.setStar(0);// 0星

				Float rate = 1.0f;

				if (parameter != null) {
					if ("3".equals(parameter)) {
						rate = NumberUtil.parseFloat(qualityUpgradeRates.get(1).getUp());
					}
				}
				JSONObject basicUpgradeExpandJsonObj = JSONUtil.parseObj(record.getBasicUpgradeExpand());
				JSONObject basicExpandJsonObj = JSONUtil.parseObj(record.getBasicExpand());
				heroBuilder.setQuality(parameter != null && "3".equals(parameter) ? 2 : 1);
				heroBuilder.setGrowHp(NumberUtil.parseInt((NumberUtil.parseInt(basicUpgradeExpandJsonObj.get("hp")+"") * rate ) + ""));
				heroBuilder.setHp(NumberUtil.parseInt((NumberUtil.parseInt(basicExpandJsonObj.get("hp")+"") * rate ) + ""));
				heroBuilder.setGrowDef(NumberUtil.parseInt((NumberUtil.parseInt(basicUpgradeExpandJsonObj.get("defense")+"") * rate ) + ""));
				heroBuilder.setDef(NumberUtil.parseInt((NumberUtil.parseInt(basicExpandJsonObj.get("defense")+"") * rate ) + ""));
				heroBuilder.setGrowAttack(NumberUtil.parseInt((NumberUtil.parseInt(basicUpgradeExpandJsonObj.get("attack")+"") * rate ) + ""));
				heroBuilder.setAttack(NumberUtil.parseInt((NumberUtil.parseInt(basicExpandJsonObj.get("attack")+"") * rate ) + ""));
				heroBuilder.setGrowSpeed(NumberUtil.parseInt((NumberUtil.parseInt(basicUpgradeExpandJsonObj.get("speed")+"") * rate ) + ""));
				heroBuilder.setSpeed(NumberUtil.parseInt((NumberUtil.parseInt(basicExpandJsonObj.get("speed")+"") * rate ) + ""));
				heroBuilder.setNumber(1);
				Long teams = redisTemplate.opsForHash().size("u:" + identifier + ":teams");
				if (teams < 5) {

					redisTemplate.opsForHash().put("u:" + identifier + ":teams", type, String.valueOf(teams + 1));
					List<PlayerItemProtobuf.PlayerItem> playerItems = new ArrayList<>();
					Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams");
					tb.forEach((k, v) -> {
						PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
								.newBuilder()
								.setKey(k + "")
								.setValue(Long.parseLong(v + ""))
								.build();
						playerItems.add(t);
					});
					playerInfo.addAllTeams(playerItems);
				}
				Long teams_pvp = redisTemplate.opsForHash().size("u:" + identifier + ":teams_pvp");
				if (teams_pvp < 5) {
					redisTemplate.opsForHash().put("u:" + identifier + ":teams_pvp", type, String.valueOf(teams_pvp + 1));

					Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams_pvp");
					List<PlayerItemProtobuf.PlayerItem> playerItems = new ArrayList<>();
					tb.forEach((k, v) -> {
						PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
								.newBuilder()
								.setKey(k + "")
								.setValue(Long.parseLong(v + ""))
								.build();
						playerItems.add(t);
					});
					playerInfo.addAllTeamsPvp(playerItems);
				}

				HeroSkillProtobuf.HeroSkill.Builder skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkillA());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder.build());

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill1());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder.build());

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill2());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder.build());

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill3());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder.build());

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill4());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder.build());

				Integer power = heroBuilder.getHp() + heroBuilder.getDef() + heroBuilder.getAttack() + heroBuilder.getSpeed();
				Map<String,String> heroData = new HashMap();
				heroData.put(heroBuilder.getType(), String.valueOf(power));
				redisTemplate.opsForHash().putAll("u:"+identifier+":heros", heroData);

				Map<String,String> hh = new HashMap();
				hh.put("id", String.valueOf(heroBuilder.getId()));
				hh.put("type", String.valueOf(heroBuilder.getType()));
				hh.put("level", String.valueOf(heroBuilder.getLevel()));
				hh.put("exp", String.valueOf(heroBuilder.getExp()));
				hh.put("star", String.valueOf(heroBuilder.getStar()));
				hh.put("quality", String.valueOf(heroBuilder.getQuality()));
				hh.put("grow_hp", String.valueOf(heroBuilder.getGrowHp()));
				hh.put("hp", String.valueOf(heroBuilder.getHp()));
				hh.put("grow_def", String.valueOf(heroBuilder.getGrowDef()));
				hh.put("def", String.valueOf(heroBuilder.getDef()));
				hh.put("grow_attack", String.valueOf(heroBuilder.getGrowAttack()));
				hh.put("attack", String.valueOf(heroBuilder.getAttack()));
				hh.put("grow_speed", String.valueOf(heroBuilder.getGrowSpeed()));
				hh.put("speed", String.valueOf(heroBuilder.getSpeed()));
				hh.put("number", String.valueOf(heroBuilder.getNumber()));
				redisTemplate.opsForHash().putAll("u:"+identifier+":"+heroBuilder.getType()+":attributes",hh);

				Map hhs = new HashMap();
				hhs.put(record.getSkillA(),"1");
				hhs.put(record.getSkill1(),"1");
				hhs.put(record.getSkill2(),"0");
				hhs.put(record.getSkill3(),"0");
				hhs.put(record.getSkill4(),"0");
				redisTemplate.opsForHash().putAll("u:"+identifier+":"+heroBuilder.getType()+":skills",hhs);

				PlayerItemProtobuf.PlayerItem updateRanklistPower = updateRanklistPower(redisTemplate, identifier);
				if (updateRanklistPower!=null) {
					playerInfo.addItems(updateRanklistPower);
				}
				playerInfo.addHeros(heroBuilder.build());

				String stringValueNickname = stringValue(redisTemplate, identifier, "nickname");
				if (stringValueNickname!=null) {
					String bcmsg = StrFormatter.format("PWBC快讯：祝贺玩家<color=red>{}</color>获得英雄<color=red>{}</color>！",stringValueNickname,record.getName());
					boardcaseWorldEvent(bcmsg);
				}
			}else {
				PlayerItemProtobuf.PlayerItem increaseItemValue = increaseItemValue(redisTemplate, identifier, "private_soulchip_" + id, NumberUtil.parseLong(record.getChips() + ""));
				playerInfo.addItems(increaseItemValue);
			}

		} else {
			log.info("invalid type");
		}
		return playerInfo.build();
	}

	public static PlayerItemProtobuf.PlayerItem appedArchivesToReply(String key, long value) {
		return PlayerItemProtobuf.PlayerItem
				.newBuilder()
				.setKey(key)
				.setValue(value)
				.build();
	}

	public static void updateRanklistPower(RedisTemplate redisTemplate, Integer identifier, PlayerInfoProtobuf.PlayerInfo.Builder reply) {
		String nickname = stringValue(redisTemplate, identifier, "nickname");
		if (nickname != null) {
			Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:power", hexEncode(nickname));
			List<Integer> powers = new ArrayList<>();

			Map heros = redisTemplate.opsForHash().entries("u:" + identifier + ":heros");
			if (heros != null) {
				heros.forEach((k, v) -> {
					powers.add(Integer.parseInt(v + ""));
				});
			}
			Collections.sort(powers);
			Collections.reverse(powers);

			Integer totalPower = 0;
			for (int i = 0; i < (Math.min(powers.size(), 5)); i++) {
				totalPower += powers.get(i);
			}
			totalPower = (totalPower / 100);
			PlayerItemProtobuf.PlayerItem item = setItemValue(redisTemplate, identifier + "", "power", totalPower);
			if (reply!=null) {
				reply.addItems(item);
			}
			__update_ranklist(redisTemplate, identifier, "power", totalPower);

			Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:power", hexEncode(nickname));
			if (!myrank.equals(omyrank) && myrank <= 100) {
				boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + nickname + "</color>战力榜提升至第" + (myrank + 1) + "名！");
			}
		}
	}

	public static void awardHeroForMe(RedisTemplate redisTemplate, Integer identifier, String type, PlayerInfoProtobuf.PlayerInfo.Builder reply, Integer parameter) {
		PlayerItemProtobuf.PlayerItem archivesItem;
		HeroSkillProtobuf.HeroSkill.Builder skillBuilder;

		boolean r = ReUtil.isMatch("^hero_(\\d+)$", type);
		if (r) {
			String id = type.split("_")[1];

			Hero record = heroHashList.get(id);

			if (redisTemplate.opsForHash().putIfAbsent("u:" + identifier + ":heros", type, 1)) {
				//onNotifyEventOfPromotions(redisTemplate, "mostheros", 1, identifier, reply);
				archivesItem = onNotifyEventOfPromotions(redisTemplate, "mostheros", 1, identifier);
				reply.addArchives(archivesItem);

				PlayerInfoProtobuf.PlayerInfo.newBuilder().build();
				HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBuilder = HeroBasicInfoProtobuf.HeroBasicInfo
						.newBuilder()
						.setId(Integer.parseInt(generateHeroIdentifier(redisTemplate)))
						.setType(type)
						.setLevel(1)    // 抽出来都是1级
						.setExp(0)        // 无经验
						.setStar(0);// 0星

				Float rate = 1.0f;

				if (parameter != null) {
					if (parameter == 3) {
						//2021/8/31 后面要替换
						// rate = RUBY_CONST_QUALITY_UPGRADE_RATE_TABLE[1]["up"].to_f
						//rate = 1.0f;
						rate = NumberUtil.parseFloat(qualityUpgradeRates.get(1).getUp());
					}
				}

				heroBuilder.setQuality(parameter != null && parameter == 3 ? 2 : 1);
				JSONObject basicUpgradeExpand = JSONUtil.parseObj(record.getBasicUpgradeExpand());
				heroBuilder.setGrowHp(Integer.parseInt(((Float) basicUpgradeExpand.get("hp") * rate) + ""));
				JSONObject basicExpand = JSONUtil.parseObj(record.getBasicExpand());
				heroBuilder.setHp(Integer.parseInt(((Float) basicExpand.get("hp") * rate) + ""));
				heroBuilder.setGrowDef(Integer.parseInt(((Float) basicUpgradeExpand.get("defense") * rate) + ""));
				heroBuilder.setDef(Integer.parseInt(((Float) basicExpand.get("defense") * rate) + ""));
				heroBuilder.setGrowAttack(Integer.parseInt(((Float) basicUpgradeExpand.get("attack") * rate) + ""));
				heroBuilder.setAttack(Integer.parseInt(((Float) basicExpand.get("attack") * rate) + ""));
				heroBuilder.setGrowSpeed(Integer.parseInt(((Float) basicUpgradeExpand.get("speed") * rate) + ""));
				heroBuilder.setSpeed(Integer.parseInt(((Float) basicExpand.get("speed") * rate) + ""));
				heroBuilder.setNumber(1);

				Long teams = redisTemplate.opsForHash().size("u:" + identifier + ":teams");
				if (teams < 5) {

					redisTemplate.opsForHash().put("u:" + identifier + ":teams", type, teams + 1);

					Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams");
					tb.forEach((k, v) -> {
						PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
								.newBuilder()
								.setKey(k + "")
								.setValue(Long.parseLong(v + ""))
								.build();
						reply.addTeams(t);
					});
				}
				Long teams_pvp = redisTemplate.opsForHash().size("u:" + identifier + ":teams_pvp");
				if (teams_pvp < 5) {
					redisTemplate.opsForHash().put("u:" + identifier + ":teams_pvp", type, teams_pvp + 1);

					Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams_pvp");
					tb.forEach((k, v) -> {
						PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
								.newBuilder()
								.setKey(k + "")
								.setValue(Long.parseLong(v + ""))
								.build();
						reply.addTeamsPvp(t);
					});
				}

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkillA());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder);

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill1());
				skillBuilder.setLevel(1);
				heroBuilder.addSkills(skillBuilder);

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill2());
				skillBuilder.setLevel(0);
				heroBuilder.addSkills(skillBuilder);

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill3());
				skillBuilder.setLevel(0);
				heroBuilder.addSkills(skillBuilder);

				skillBuilder = HeroSkillProtobuf.HeroSkill.newBuilder();
				skillBuilder.setType(record.getSkill4());
				skillBuilder.setLevel(0);
				heroBuilder.addSkills(skillBuilder);

				HeroBasicInfoProtobuf.HeroBasicInfo hero = heroBuilder.build();

				int power = heroBuilder.getHp() + heroBuilder.getDef() + heroBuilder.getAttack() + heroBuilder.getSpeed();
				Map<String, Object> heroPowerMap = new HashMap<>();
				heroPowerMap.put(heroBuilder.getType(), power);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":heros", heroPowerMap);

				Map<String, Object> hh = new HashMap<>();
				hh.put("id", heroBuilder.getId());
				hh.put("type", heroBuilder.getType());
				hh.put("level", heroBuilder.getLevel());
				hh.put("exp", heroBuilder.getExp());
				hh.put("star", heroBuilder.getStar());
				hh.put("quality", heroBuilder.getQuality());
				hh.put("grow_hp", heroBuilder.getGrowHp());
				hh.put("hp", heroBuilder.getHp());
				hh.put("grow_def", heroBuilder.getGrowDef());
				hh.put("def", heroBuilder.getDef());
				hh.put("grow_attack", heroBuilder.getGrowAttack());
				hh.put("attack", heroBuilder.getAttack());
				hh.put("grow_speed", heroBuilder.getGrowSpeed());
				hh.put("speed", heroBuilder.getSpeed());
				hh.put("number", heroBuilder.getNumber());
				redisTemplate.opsForHash().putAll("u:" + identifier + ":" + heroBuilder.getType() + ":attributes", hh);

				Map<String, Object> hhs = new HashMap<>();
				hhs.put(record.getSkillA(), 1);
				hhs.put(record.getSkill1(), 1);
				hhs.put(record.getSkill2(), 0);
				hhs.put(record.getSkill3(), 0);
				hhs.put(record.getSkill4(), 0);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":" + heroBuilder.getType() + ":skills", hhs);

				updateRanklistPower(redisTemplate, identifier, reply);
				if (reply != null) {
					reply.addHeros(hero);
				}

				if (stringValue(redisTemplate, identifier, "nickname") != null) {
					String bcmsg = StrUtil.format("PWBC快讯：祝贺玩家<color=red>{}</color>获得英雄<color=red>{}</color>！", stringValue(redisTemplate, identifier, "nickname"), record.getName());
					boardcaseWorldEvent(bcmsg);
				}
			} else {
				//hero already exists
				PlayerItemProtobuf.PlayerItem item = increaseItemValue(redisTemplate, identifier, "private_soulchip_" + id, NumberUtil.parseLong(record.getChips() + ""));
				reply.addItems(item);
			}

		} else {
			log.info("invalid type");
		}
	}


	public static PlayerItemProtobuf.PlayerItem onNotifyEventOfPromotions(RedisTemplate redisTemplate, String key, Integer parameter, Integer identifier) {
		return promotionFoo(key, parameter, redisTemplate, identifier);
	}

	public static PlayerItemProtobuf.PlayerItem promotionFoo(String key, Integer parameter, RedisTemplate redisTemplate, Integer identifier) {
		PlayerItemProtobuf.PlayerItem item = null;
		Object now = null;
		Map map = null;
		switch (key) {
			case "type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_1_monster_kill", parameter);
				item = appedArchivesToReply("type_1_monster_kill", Long.parseLong(now + ""));
				break;
			case "type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_2_monster_kill", parameter);
				item = appedArchivesToReply("type_2_monster_kill", Long.parseLong(now + ""));
				break;
			case "type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_3_monster_kill", parameter);
				item = appedArchivesToReply("type_3_monster_kill", Long.parseLong(now + ""));
				break;
			case "type_1_boss_kill":
				map = new HashMap();
				map.put("type_1_boss_kill", parameter);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				item = appedArchivesToReply("type_1_boss_kill", parameter);
				break;
			case "type_2_boss_kill":
				map = new HashMap();
				map.put("type_2_boss_kill", parameter % 1000);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				item = appedArchivesToReply("type_2_boss_kill", parameter % 1000);
				break;
			case "type_3_boss_kill":
				map = new HashMap();
				map.put("type_3_boss_kill", parameter % 1000);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				item = appedArchivesToReply("type_3_boss_kill", parameter % 1000);
				break;
			case "vip":
				map = new HashMap();
				map.put("vip", parameter);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				item = appedArchivesToReply("vip", parameter);
				break;
			case "cuthand":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "cuthand", parameter);
				item = appedArchivesToReply("cuthand", Long.parseLong(now + ""));
				break;
			case "mostheros":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mostheros", parameter);
				item = appedArchivesToReply("mostheros", Long.parseLong(now + ""));
				break;
			case "mosthire":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mosthire", parameter);
				item = appedArchivesToReply("mosthire", Long.parseLong(now + ""));
				break;
			case "kingofpvp":
				Object kingofpvpValue = redisTemplate.opsForHash().get("u:" + identifier + ":archives", "kingofpvp");

				Map archives = new HashMap();

				//Map archives = (Map) redisTemplate.opsForHash().get("u:" + identifier + ":archives", "kingofpvp");
				log.info("{}", archives);
				log.info("{}, {}", identifier, parameter);
				if (kingofpvpValue == null) {
					archives.put("kingofpvp", String.valueOf(parameter));
					redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
					item = appedArchivesToReply("kingofpvp", parameter);
				} else {
					if (kingofpvpValue != null && parameter <= Integer.parseInt(kingofpvpValue + "")) {
						archives.put("kingofpvp", String.valueOf(parameter));
						redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
						item = appedArchivesToReply("kingofpvp", parameter);
					}
				}
				break;
			case "tempbackpack":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "tempbackpack", parameter);
				item = appedArchivesToReply("tempbackpack", Long.parseLong(now + ""));
				break;
			case "expbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "expbooks", parameter);
				item = appedArchivesToReply("expbooks", Long.parseLong(now + ""));
				break;
			case "consumeexpbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "consumeexpbooks", parameter);
				item = appedArchivesToReply("consumeexpbooks", Long.parseLong(now + ""));
				break;
			case "maxgold":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxgold", parameter);
				item = appedArchivesToReply("maxgold", Long.parseLong(now + ""));
				break;
			case "maxhonor":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxhonor", parameter);
				item = appedArchivesToReply("maxhonor", Long.parseLong(now + ""));
				break;
			case "maxtopskills":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxtopskills", parameter);
				item = appedArchivesToReply("maxtopskills", Long.parseLong(now + ""));
				break;
			case "daily_skill_upgrade":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_skill_upgrade", parameter);
				item = appedArchivesToReply("daily_skill_upgrade", Long.parseLong(now + ""));
				break;
			case "daily_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_monster_kill", parameter);
				item = appedArchivesToReply("daily_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_pvp_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_pvp_times", parameter);
				item = appedArchivesToReply("daily_pvp_times", Long.parseLong(now + ""));
				break;
			case "daily_exp_book_consume":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_exp_book_consume", parameter);
				item = appedArchivesToReply("daily_exp_book_consume", Long.parseLong(now + ""));
				break;
			case "daily_buy_hero":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_buy_hero", parameter);
				item = appedArchivesToReply("daily_buy_hero", Long.parseLong(now + ""));
				break;
			case "daily_type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_1_monster_kill", parameter);
				item = appedArchivesToReply("daily_type_1_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_2_monster_kill", parameter);
				item = appedArchivesToReply("daily_type_2_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_3_monster_kill", parameter);
				item = appedArchivesToReply("daily_type_3_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_store_buy_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_store_buy_times", parameter);
				item = appedArchivesToReply("daily_store_buy_times", Long.parseLong(now + ""));
				break;
			default:
				break;
		}
		return item;
	}


	public static long currentTimestamp() {
		return System.currentTimeMillis() / 1000 + 28800;
	}

	public static long currentDay() {
		return (System.currentTimeMillis() / 1000 + 28800) / 24 / 3600;
	}

	public static PlayerItemProtobuf.PlayerItem updateRanklistPower(RedisTemplate redisTemplate, Integer identifier) {
		String nickname = stringValue(redisTemplate, identifier, "nickname");
		PlayerItemProtobuf.PlayerItem playerItem = null;
		if (nickname != null && !"".equals(nickname)) {
			Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:power", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
			List<Integer> powers = new ArrayList<>();

			Map<Object, Object> map = redisTemplate.opsForHash().entries("u:" + identifier + ":heros");
			if (map.size() > 0) {
				for (Object value : map.values()) {
					powers.add(ToUtil.to_i(value));
				}
				//倒叙排序
				powers.sort(Comparator.reverseOrder());
			}

			int totalPower = 0;

			for (int i = 0; i < (Math.min(powers.size(), 5)); i++) {
				totalPower = totalPower + ToUtil.to_i(powers.get(i));
			}
			totalPower = ToUtil.to_i(totalPower / 100.0);

			playerItem = setItemValue(redisTemplate, String.valueOf(identifier), "power", totalPower);

			__update_ranklist(redisTemplate, identifier, "power", totalPower);

			Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:power", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
			if (myrank != null) {
				if (!myrank.equals(omyrank) && myrank <= 100 && stringValue(redisTemplate, identifier, "nickname") != null) {
					try {
						String nickName = new String(stringValue(redisTemplate, identifier, "nickname").getBytes("UTF-8"), "UTF-8");
						boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + nickName + "</color>战力榜提升至第" + myrank + "名！");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return playerItem;
	}

	public static PlayerItemProtobuf.PlayerItem updateRanklistHonor(RedisTemplate redisTemplate, Integer identifier) {
		Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		__update_ranklist(redisTemplate, identifier, "honor", itemCount(redisTemplate, identifier, "total_honor"));

		Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		if (omyrank != myrank && myrank <= 100 && hexEncode(stringValue(redisTemplate, identifier, "nickname")) != null) {
			boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + stringValue(redisTemplate, identifier, "nickname") + "</color>荣誉榜提升至第" + (myrank + 1) + "名！");
		}
		return onNotifyEventOfPromotions(redisTemplate, "kingofpvp", Integer.valueOf((myrank + 1) + ""), identifier);

	}

	public static void updateRanklistRich(RedisTemplate redisTemplate, Integer identifier) {
		Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		__update_ranklist(redisTemplate, identifier, "rich", itemCount(redisTemplate, identifier, "total_stone_purchased"));

		Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:rich", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		if (!myrank.equals(omyrank) && myrank <= 100 && stringValue(redisTemplate, identifier, "nickname") != null) {
			boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + stringValue(redisTemplate, identifier, "nickname") + "</color>战力榜提升至第" + (myrank + 1) + "名！");
		}
	}

	public static void __update_ranklist(RedisTemplate redisTemplate, Integer identifier, String ranklist, Integer score) {
		redisTemplate.opsForZSet().add("ranklist:" + ranklist, hexEncode(stringValue(redisTemplate, identifier, "nickname")), Double.valueOf(score));
	}


	public static List<JSONObject> refreshNewStoreNowEx(JSONArray table) {
		log.info("refresh_new_store_now_ex");

		long weekOfYear = currentDay();

		Map types = new HashMap();

		table.forEach(json -> {
			JSONObject jsonObject = (JSONObject) json;
			types.put(NumberUtil.parseInt(jsonObject.get("type") + ""), jsonObject.get("type"));
		});

		List<JSONObject> items = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			JSONObject currentIndexObj = (JSONObject) table.get(i);
			JSONObject f = JSONUtil.parseObj(currentIndexObj.get("goodFomula"));

			Integer currentType = (Integer) currentIndexObj.get("type");
			if (currentType != 0) {
				if ((weekOfYear % types.size() + 1) != currentType) {
					continue;
				}
			}

			JSONObject item = new JSONObject();
			item.putOnce("name", StrFormatter.format("{}_{}", f.get("good_prefix"),
					(RandomUtil.randomInt(Integer.parseInt(f.get("to") + "") -
							Integer.parseInt(f.get("from") + "") + 1) +
							Integer.parseInt(f.get("from") + ""))));
			item.putOnce("number", RandomUtil.randomInt(100) < Integer.parseInt(f.get("factor") + "") ? Integer.parseInt(f.get("factor") + "") : 0);
			item.putOnce("cost_type", f.get("cost"));
			item.putOnce("cost_number", f.get("cost_number"));

			items.add(item);
		}

		return items;
	}

	public static List<StoreItemProtobuf.StoreItem> refreshStore(RedisTemplate redisTemplate, Integer identifier, Integer type) {
		List<StoreItemProtobuf.StoreItem> storeItemList = new ArrayList<>();
		JSONArray rubyConstNewStoreHotTable = new JSONArray();
		JSONArray rubyConstNewStoreDiscountTable = new JSONArray();
		JSONArray rubyConstNewStoreTimeLimitTable = new JSONArray();
		for (NewStoreHot newStoreHot : newStoreHots) {
			rubyConstNewStoreHotTable.add(JSONUtil.parse(newStoreHot));
		}
		for (NewStoreDiscount newStoreDiscount : newStoreDiscounts) {
			rubyConstNewStoreDiscountTable.add(JSONUtil.parse(newStoreDiscount));
		}
		for (NewStoreTimeLimit newStoreTimeLimit : newStoreTimeLimits) {
			rubyConstNewStoreTimeLimitTable.add(JSONUtil.parse(newStoreTimeLimit));
		}
		JSONArray tables = new JSONArray();
		tables.add(rubyConstNewStoreHotTable);
		tables.add(rubyConstNewStoreDiscountTable);
		tables.add(rubyConstNewStoreTimeLimitTable);

		if (type >= 1 && type <= 3) {
			JSONArray table = (JSONArray) tables.get(type - 1);
			List<JSONObject> items = refreshNewStoreNowEx(table);

			Map h = new HashMap();

			for (int i = 0; i < items.size(); i++) {
				h.put((i + 1) + "", JSONUtil.toJsonStr(items.get(i)));
			}

			redisTemplate.opsForHash().putAll("u:" + identifier + ":store:" + type, h);

			for (int i = 0; i < items.size(); i++) {
				JSONObject item = items.get(i);
				StoreItemProtobuf.StoreItem storeItem = StoreItemProtobuf.StoreItem
						.newBuilder()
						.setName(item.get("name") + "")
						.setNumber(Integer.parseInt(item.get("number") + ""))
						.setCostType(item.get("cost_type") + "")
						.setCostNumber(Integer.parseInt(item.get("cost_number") + ""))
						.build();
				//源代码是添加商品
				//store.goods.push(g)
				storeItemList.add(storeItem);
			}

		} else {
			log.info("unknow store type");
		}
		return storeItemList;
	}


	/**
	 * 更新临时背包
	 *
	 * @param dropService
	 * @param redisTemplate
	 * @param identifier
	 * @param monsters
	 * @param goblins
	 * @return
	 */
	public static PlayerInfoProtobuf.PlayerInfo updateTemporaryBackpack(DropService dropService, StageService stageService, BackpackService backpackService
			, RedisTemplate redisTemplate, Integer identifier, Integer monsters,
																		Integer goblins) {
		PlayerInfoProtobuf.PlayerInfo.Builder resultBuilder = PlayerInfoProtobuf.PlayerInfo.newBuilder();
		Formatter m = new Formatter();
		Map<Object, Object> pack = redisTemplate.opsForHash().entries(m.format("u:%d:temp_backpack", identifier).toString());
		m = new Formatter();
		PlayerItemProtobuf.PlayerItem playerItem = onNotifyEventOfPromotions(redisTemplate, m.format("type_%s_monster_kill", pack.get("type").toString()).toString(), monsters, identifier);
		PlayerItemProtobuf.PlayerItem playerItem1 = onNotifyEventOfPromotions(redisTemplate, "daily_monster_kill", monsters, identifier);
		m = new Formatter();
		PlayerItemProtobuf.PlayerItem playerItem2 = onNotifyEventOfPromotions(redisTemplate, m.format("daily_type_%s_monster_kill", pack.get("type").toString()).toString(), monsters, identifier);
		resultBuilder.addArchives(playerItem);
		resultBuilder.addArchives(playerItem1);
		resultBuilder.addArchives(playerItem2);
		Integer vip = itemCount(redisTemplate, identifier, "vip");

		Long duration = current_timestamp() - NumberUtil.parseLong(pack.get("dungeon_enter_timestamp").toString());

		ConstStageTableItemExProtobuf.ConstStageTableItemEx constStr = stageService.getExById(NumberUtil.parseInt(pack.get("stage").toString()));

		int level = ToUtil.to_i(pack.get("level")) ;

		long exp_delta = duration * constStr.getBasicAwardFomula().getExp();

		m = new Formatter();
		if (redisTemplate.opsForHash().hasKey(m.format("u:#{identifier}:items", identifier).toString(), "double_exp_card_2")) {
			double ratio = 1.0;
			if (vip != 0) {
				ratio = 2 + vip / 10.0;
			}
			exp_delta *= ratio;
		}

		m = new Formatter();
		Integer exp_max = backpackService.getByLevel(level).getExpMax();
		if (redisTemplate.opsForHash().increment(m.format("u:%d:temp_backpack_items", identifier).toString(), "exp", exp_delta) >
				exp_max) {
			m = new Formatter();
			redisTemplate.opsForHash().put(m.format("u:%d:temp_backpack_items", identifier).toString(), "exp", exp_max);
		}

		long gold_delta = duration * constStr.getBasicAwardFomula().getGold();

		Integer gold_max = backpackService.getByLevel(level).getGoldMax();
		if (redisTemplate.opsForHash().increment(String.format("u:%d:temp_backpack_items", identifier), "gold", gold_delta) >
				gold_max) {
			redisTemplate.opsForHash().put(String.format("u:%d:temp_backpack_items", identifier), "gold", gold_max);
		}

		//计算小怪收益
		if (monsters != null && monsters > 0) {
			Integer itemCount = itemCount(redisTemplate, identifier, String.format("dungeon_%s_not_passed_stage", pack.get("type")));
			String drop_action = itemCount.equals(ToUtil.to_i(pack.get("stage"))) ? constStr.getMonstersKilledAwardFomula().getDropid() : constStr.getMonstersKilledAwardFomula().getDropidBosskilled();
			for (int i = 0; i <= monsters; i++) {
				PlayerInfoProtobuf.PlayerInfo playerInfo = dropService
						.dropItem(drop_action, identifier, true, null);
				resultBuilder.addAllHeros(playerInfo.getHerosList());
				resultBuilder.addAllArchives(playerInfo.getArchivesList());
				resultBuilder.addAllItems(playerInfo.getItemsList());
				resultBuilder.addAllTeams(playerInfo.getTeamsList());
				resultBuilder.addAllTeamsPvp(playerInfo.getTeamsPvpList());
			}
		}

		//计算哥布林收益
		if (goblins != null && goblins > 0) {
			for (int i = 0; i <= goblins; i++) {
				String drop_goblin = constStr.getGoblinFomula().getDropid();
				PlayerInfoProtobuf.PlayerInfo playerInfo = dropService.dropItem(drop_goblin, identifier, true, null);
				resultBuilder.addAllItems(playerInfo.getItemsList());
				resultBuilder.addAllArchives(playerInfo.getArchivesList());
				resultBuilder.addAllHeros(playerInfo.getHerosList());
				resultBuilder.addAllTeams(playerInfo.getTeamsList());
				resultBuilder.addAllTeamsPvp(playerInfo.getTeamsPvpList());
			}
		}

		Map<String, Object> items = redisTemplate.opsForHash().entries(String.format("u:%d:temp_backpack_items", identifier));
		Set<String> itemsKey = items.keySet();
		for (String s : itemsKey) {
			PlayerItemProtobuf.PlayerItem.Builder builder = PlayerItemProtobuf.PlayerItem.newBuilder();
			builder.setKey(s);
			builder.setValue(ToUtil.to_i(items.get(s)));
			resultBuilder.addItems(builder.build());
		}

		redisTemplate.opsForHash().put(String.format("u:%d:temp_backpack", identifier), "dungeon_enter_timestamp", current_timestamp());

		return resultBuilder.build();
	}

	private static long current_timestamp() {
		return System.currentTimeMillis() / 1000 + 28800;
	}
}
