package com.gejian.pixel.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.Generated;
import com.gejian.pixel.proto.*;
import io.netty.channel.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年08月30日 10:29
 * @description 公共工具类
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class Helper {

	private final Generated generated;

	private JSONArray skillToHero = new JSONArray();

	private void heroNameToSkillBookHash() {
		JSONArray rubyConstHeroTable = generated.getRUBY_CONST_HERO_TABLE();
		if (rubyConstHeroTable != null) {
			for (Object o : rubyConstHeroTable) {
				JSONObject constHero = (JSONObject) o;
				JSONObject hero = new JSONObject();
				hero.putOnce("skilla", constHero.get("name"));
				hero.putOnce("skill1", constHero.get("name"));
				hero.putOnce("skill2", constHero.get("name"));
				hero.putOnce("skill3", constHero.get("name"));
				hero.putOnce("skill4", constHero.get("name"));
				skillToHero.add(hero);
			}
		}
	}

	@PostConstruct
	public void init() {
		heroNameToSkillBookHash();
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
	 * @param reply         回复的消息
	 */
	public static PlayerItemProtobuf.PlayerItem setItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta) {
		redisTemplate.opsForHash().put("u:" + identifier + ":items", name, delta);
		return PlayerItemProtobuf.PlayerItem.newBuilder()
				.setKey(name)
				.setValue(delta)
				.build();

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
		if (delta > 0) {
			Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			long current = Long.parseLong(result + "");
			builder.setValue(current);
			return builder.build();
		} else if (delta < 0) {
			Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			long current = Long.parseLong(result + "");
			if (current < 0) {
				Long increment = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta * -1);
				builder.setValue(increment);
				return builder.build();
			}
			builder.setValue(current);
			return builder.build();
		}
		builder.setValue(0);
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
		return increaseItemValue(redisTemplate, identifier, name, delta);
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
		log.info("{} boardcast_world_event => {}", DateUtil.now(), desc);
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

	public static void awardHeroForMe(RedisTemplate redisTemplate, Integer identifier, String type, Channel reply, Integer parameter) {
		boolean r = ReUtil.isMatch("^hero_(\\d+)$", type);
		if (r) {
			String id = type.split("_")[1];

			// TODO: 2021/8/31 后面要替换
			//String[][] record = RUBY_CONST_HERO_TABLE_HASH["X#{id}"]
			Map<String, Map> record = null;

			if (redisTemplate.opsForHash().putIfAbsent("u:" + identifier + ":heros", type, 1)) {
				onNotifyEventOfPromotions(redisTemplate, "mostheros", 1, identifier, reply);
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
						// TODO: 2021/8/31 后面要替换
						// rate = RUBY_CONST_QUALITY_UPGRADE_RATE_TABLE[1]['up'].to_f
						rate = 1.0f;
					}
				}

				heroBuilder.setQuality(parameter != null && parameter == 3 ? 2 : 1);
				heroBuilder.setGrowHp(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("hp") * rate) + ""));
				heroBuilder.setHp(Integer.parseInt(((Float) record.get("basic_expand").get("hp") * rate) + ""));
				heroBuilder.setGrowDef(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("defense") * rate) + ""));
				heroBuilder.setDef(Integer.parseInt(((Float) record.get("basic_expand").get("defense") * rate) + ""));
				heroBuilder.setGrowAttack(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("attack") * rate) + ""));
				heroBuilder.setAttack(Integer.parseInt(((Float) record.get("basic_expand").get("attack") * rate) + ""));
				heroBuilder.setGrowSpeed(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("speed") * rate) + ""));
				heroBuilder.setSpeed(Integer.parseInt(((Float) record.get("basic_expand").get("speed") * rate) + ""));
				heroBuilder.setNumber(1);

				Long teams = redisTemplate.opsForHash().size("u:" + identifier + ":teams");
				if (teams < 5) {

					redisTemplate.opsForHash().put("u:" + identifier + ":teams", type, teams + 1);

					if (reply != null) {
						Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams");
						tb.forEach((k, v) -> {
							PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
									.newBuilder()
									.setKey(k + "")
									.setValue(Long.parseLong(v + ""))
									.build();
							reply.writeAndFlush(t);
						});
					}
				}
				Long teams_pvp = redisTemplate.opsForHash().size("u:" + identifier + ":teams_pvp");
				if (teams_pvp < 5) {
					redisTemplate.opsForHash().put("u:" + identifier + ":teams_pvp", type, teams_pvp + 1);

					if (reply != null) {
						Map tb = redisTemplate.opsForHash().entries("u:" + identifier + ":teams_pvp");
						tb.forEach((k, v) -> {
							PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
									.newBuilder()
									.setKey(k + "")
									.setValue(Long.parseLong(v + ""))
									.build();
							reply.writeAndFlush(t);
						});
					}
				}
			}

		} else {
			log.info("invalid type");
		}
	}

	public static void appedArchivesToReply(Channel reply, String key, long value) {
		if (reply != null) {
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(key)
					.setValue(value)
					.build();
			reply.writeAndFlush(item);
		}
	}

	public static void promotionFoo(String key, Integer parameter, RedisTemplate redisTemplate, Integer identifier, Channel reply) {
		Object now = null;
		Map map = null;
		switch (key) {
			case "type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_1_monster_kill", parameter);
				appedArchivesToReply(reply, "type_1_monster_kill", Long.parseLong(now + ""));
				break;
			case "type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_2_monster_kill", parameter);
				appedArchivesToReply(reply, "type_2_monster_kill", Long.parseLong(now + ""));
				break;
			case "type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_3_monster_kill", parameter);
				appedArchivesToReply(reply, "type_3monster_kill", Long.parseLong(now + ""));
				break;
			case "type_1_boss_kill":
				map = new HashMap();
				map.put("type_1_boss_kill", parameter);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				appedArchivesToReply(reply, "type_1_boss_kill", parameter);
				break;
			case "type_2_boss_kill":
				map = new HashMap();
				map.put("type_2_boss_kill", parameter % 1000);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				appedArchivesToReply(reply, "type_2_boss_kill", parameter % 1000);
				break;
			case "type_3_boss_kill":
				map = new HashMap();
				map.put("type_3_boss_kill", parameter % 1000);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				appedArchivesToReply(reply, "type_3_boss_kill", parameter % 1000);
				break;
			case "vip":
				map = new HashMap();
				map.put("vip", parameter);
				redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", map);
				appedArchivesToReply(reply, "vip", parameter);
				break;
			case "cuthand":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "cuthand", parameter);
				appedArchivesToReply(reply, "cuthand", Long.parseLong(now + ""));
				break;
			case "mostheros":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mostheros", parameter);
				appedArchivesToReply(reply, "mostheros", Long.parseLong(now + ""));
				break;
			case "mosthire":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mosthire", parameter);
				appedArchivesToReply(reply, "mosthire", Long.parseLong(now + ""));
				break;
			case "kingofpvp":
				Map archives = (HashMap) redisTemplate.opsForHash().get("u:" + identifier + ":archives", "kingofpvp");
				log.info("{}", archives);
				log.info("{} => {}", identifier, parameter);
				if (archives != null) {
					if (archives.get("kingofpvp") == null) {
						archives.put("kingofpvp", parameter);
						redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
						appedArchivesToReply(reply, "kingofpvp", parameter);
					} else {
						if (archives.get("kingofpvp") != null && parameter <= Integer.parseInt(archives.get("kingofpvp") + "")) {
							archives.put("kingofpvp", parameter);
							redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
							appedArchivesToReply(reply, "kingofpvp", parameter);
						}
					}
				}
				break;
			case "tempbackpack":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "tempbackpack", parameter);
				appedArchivesToReply(reply, "tempbackpack", Long.parseLong(now + ""));
				break;
			case "expbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "expbooks", parameter);
				appedArchivesToReply(reply, "expbooks", Long.parseLong(now + ""));
				break;
			case "consumeexpbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "consumeexpbooks", parameter);
				appedArchivesToReply(reply, "consumeexpbooks", Long.parseLong(now + ""));
				break;
			case "maxgold":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxgold", parameter);
				appedArchivesToReply(reply, "maxgold", Long.parseLong(now + ""));
				break;
			case "maxhonor":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxhonor", parameter);
				appedArchivesToReply(reply, "maxhonor", Long.parseLong(now + ""));
				break;
			case "maxtopskills":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxtopskills", parameter);
				appedArchivesToReply(reply, "maxtopskills", Long.parseLong(now + ""));
				break;
			case "daily_skill_upgrade":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_skill_upgrade", parameter);
				appedArchivesToReply(reply, "daily_skill_upgrade", Long.parseLong(now + ""));
				break;
			case "daily_pvp_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_pvp_times", parameter);
				appedArchivesToReply(reply, "daily_pvp_times", Long.parseLong(now + ""));
				break;
			case "daily_exp_book_consume":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_exp_book_consume", parameter);
				appedArchivesToReply(reply, "daily_exp_book_consume", Long.parseLong(now + ""));
				break;
			case "daily_buy_hero":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_buy_hero", parameter);
				appedArchivesToReply(reply, "daily_buy_hero", Long.parseLong(now + ""));
				break;
			case "daily_type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_1_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_1_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_2_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_2_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_3_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_3_monster_kill", Long.parseLong(now + ""));
				break;
			case "daily_store_buy_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_store_buy_times", parameter);
				appedArchivesToReply(reply, "daily_store_buy_times", Long.parseLong(now + ""));
				break;
			default:
				break;
		}
	}

	public static void onNotifyEventOfPromotions(RedisTemplate redisTemplate, String key, Integer parameter, Integer identifier, Channel reply) {
		promotionFoo(key, parameter, redisTemplate, identifier, reply);
	}

	public static long currentTimestamp() {
		return System.currentTimeMillis() / 1000 + 28800;
	}

	public static long currentDay() {
		return (System.currentTimeMillis() / 1000 + 28800) / 24 / 3600;
	}

	public static void updateRanklistHonor(RedisTemplate redisTemplate, Integer identifier, Channel reply) {
		Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		__update_ranklist(redisTemplate, identifier, "honor", itemCount(redisTemplate, identifier, "total_honor"));

		Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor", hexEncode(stringValue(redisTemplate, identifier, "nickname")));
		if (omyrank != myrank && myrank <= 100 && hexEncode(stringValue(redisTemplate, identifier, "nickname")) != null) {
			boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>" + stringValue(redisTemplate, identifier, "nickname") + "</color>荣誉榜提升至第" + (myrank + 1) + "名！");
		}
		onNotifyEventOfPromotions(redisTemplate, "kingofpvp", Integer.valueOf((myrank + 1) + ""), identifier, reply);

	}

	public static void __update_ranklist(RedisTemplate redisTemplate, Integer identifier, String ranklist, Integer score) {
		redisTemplate.opsForZSet().add("ranklist:" + ranklist, score, Integer.parseInt(hexEncode(stringValue(redisTemplate, identifier, "nickname"))));
	}

	//获取奖励
	public static void getAward(RedisTemplate redisTemplate, Integer identifier, List<Object> ar, Channel reply, Boolean store2backpack, Integer parameter) {
		Generated generated = new Generated();

		String type = (String) ar.get(0);

		String[] arIndex3Arr = (String[]) ar.get(3);
		if (arIndex3Arr.length > 1) {
			type = arIndex3Arr[RandomUtil.randomInt(arIndex3Arr.length)];
		} else {
			type = arIndex3Arr[0];
		}

		int value = 0;
		Object[] arIndex2Arr = (Object[]) ar.get(2);
		if (arIndex2Arr.length <= 1) {
			value = Integer.parseInt(arIndex2Arr[0] + "");
		} else {
			value = RandomUtil.randomInt(Integer.parseInt(arIndex2Arr[1] + "")) + Integer.parseInt(arIndex2Arr[0] + "");
		}

		if (store2backpack != null) {
			if (store2backpack) {
				if (ReUtil.isMatch("^box_monry_.*$", type) ||
						ReUtil.isMatch("^box_exp_book_.*$", type) ||
						ReUtil.isMatch("^box_private_soulchip_.*$", type) ||
						ReUtil.isMatch("^box_skillbook_.*$", type) ||
						ReUtil.isMatch("^box_stone_.*$", type)) {
					generated.dropItems(redisTemplate, type, identifier, reply, store2backpack, parameter);
				}
			} else if ((type.equals("gold") ||
					type.equals("exp") ||
					type.equals("stone") ||
					type.equals("honer") ||
					ReUtil.isMatch("^exp_book_.*$", type)) ||
					ReUtil.isMatch("^private_soulchip_.*$", type) ||
					ReUtil.isMatch("^book_skill_.*$", type)) {
				Map<String, Integer> pack = redisTemplate.opsForHash().entries("u:" + identifier + ":temp_backpack");
				if (pack != null) {
					pack.forEach((k, v) -> {
						pack.put(k, v);
					});
				}
				pack.put("level", pack.get("level") - 1);

				if (type.equals("gold")) {
					//金币增量
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer goldMax = (Integer) jsonObject.get("gold_max");
					if (redisTemplate.opsForHash().increment("u:" + identifier + ":temp_backpack_items", type, value) > goldMax) {
						redisTemplate.opsForHash().put("u:" + identifier + ":temp_backpack_items", type, goldMax);
					}
				} else if (type.equals("exp")) {
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer expMax = (Integer) jsonObject.get("exp_max");
					if (redisTemplate.opsForHash().increment("u:" + identifier + ":temp_backpack_items", type, value) > expMax) {
						redisTemplate.opsForHash().put("u:" + identifier + ":temp_backpack_items", type, expMax);
					}
				} else {
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer itemMax = (Integer) jsonObject.get("item_max");
					if (redisTemplate.opsForHash().size("u:" + identifier + ":temp_backpack_items") > itemMax + 2) {
						redisTemplate.opsForHash().increment("u:" + identifier + ":temp_backpack_items", type, value);
					} else {
						log.info("exceed temp backpack max");
					}
				}
			} else if (ReUtil.isMatch("^hero_.*$", type)) {
				log.info("can not handle award hero");
			} else if (type.equals("dummy")) {
				log.info("do nothing");
			} else {
				log.info("unknow award type {}", type);
			}
		} else {
			if (ReUtil.isMatch("^box_monry_.*$", type) ||
					ReUtil.isMatch("^box_exp_book_.*$", type) ||
					ReUtil.isMatch("^box_private_soulchip_.*$", type) ||
					ReUtil.isMatch("^box_skillbook_.*$", type) ||
					ReUtil.isMatch("^box_stone_.*$", type)) {
				generated.dropItems(redisTemplate, type, identifier, reply, store2backpack, parameter);
			} else if ((type.equals("gold") ||
					type.equals("stone") ||
					type.equals("honor") ||
					ReUtil.isMatch("^exp_book_.*$", type)) ||
					ReUtil.isMatch("^private_soulchip_.*$", type) ||
					ReUtil.isMatch("^book_skill_.*$", type)) {
				// TODO: 2021/9/2 需要更改replay为消息对象，然后设值
				//increaseItemValue(redisTemplate, identifier, type, value, reply);
				PlayerItemProtobuf.PlayerItem playerItem = increaseItemValue(redisTemplate, identifier, type, Long.valueOf(value + ""));

				if (type.equals("honor")) {
					if (parameter != null && parameter.equals("archives")) {
					} else {
						// TODO: 2021/9/2 需要更改replay为消息对象，然后设值
						//increaseItemValue(redisTemplate, identifier, type, value, reply);
						playerItem = increaseItemValue(redisTemplate, identifier, type, Long.valueOf(value + ""));
						updateRanklistHonor(redisTemplate, identifier, reply);
						onNotifyEventOfPromotions(redisTemplate, "maxhonor", value, identifier, reply);
					}
				}

				if (ReUtil.isMatch("^exp_book_.*$", type)) {
					onNotifyEventOfPromotions(redisTemplate, "expbooks", value, identifier, reply);
				} else if (type.equals("gold")) {
					onNotifyEventOfPromotions(redisTemplate, "maxgold", value, identifier, reply);
				} else {
				}
			} else if (type.equals("exp")) {
				//上阵角色都加经验
				Long nbHerosInTeam = redisTemplate.opsForHash().size("u:" + identifier + ":teams");
				Map<String, Object> teams = redisTemplate.opsForHash().entries("u:" + identifier + ":heros");
				teams.forEach((k, v) -> {
					Map<String, Integer> attributes = redisTemplate.opsForHash().entries("u:" + identifier + ":" + k + ":attributes");
					Map skills = redisTemplate.opsForHash().entries("u:" + identifier + ":" + k + ":skills");

					attributes.forEach((k1, v1) -> {
						attributes.put(k1, k1.equals("type") ? v1 : Integer.parseInt(v1 + ""));
					});

					attributes.put("exp", attributes.get("exp") + (Integer.parseInt(v + "") / Integer.parseInt(nbHerosInTeam + "")));

					while (attributes.get("level") < 99) {
						JSONArray rubyConstLevelUpgradeTable = generated.getRUBY_CONST_LEVEL_UPGRADE_TABLE();
						Map<String, Integer> item = (Map) rubyConstLevelUpgradeTable.get(attributes.get("level"));
						Integer expNeed = item.get("start" + attributes.get("start"));
						if (attributes.get("exp") >= expNeed) {
							attributes.put("exp", attributes.get("exp") - expNeed);
							attributes.put("level", attributes.get("level") + 1);
							attributes.put("hp", attributes.get("hp") + attributes.get("grow_hp"));
							attributes.put("def", attributes.get("def") + attributes.get("grow_def"));
							attributes.put("attack", attributes.get("attack") + attributes.get("grow_attack"));
							attributes.put("speed", attributes.get("speed") + attributes.get("grow_speed"));
							if (attributes.get("exp") == expNeed) {
								attributes.put("level", 99);
							}
						} else {
							break;
						}
					}

					redisTemplate.opsForHash().putAll("u:" + identifier + ":" + k + ":attributes", attributes);

					HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBuilder = HeroBasicInfoProtobuf.HeroBasicInfo.newBuilder();

					heroBuilder.setExp(attributes.get("exp"));
					heroBuilder.setLevel(attributes.get("level"));
					heroBuilder.setHp(attributes.get("hp"));
					heroBuilder.setHp(attributes.get("hp"));
					heroBuilder.setGrowHp(attributes.get("grow_hp"));
					heroBuilder.setDef(attributes.get("def"));
					heroBuilder.setGrowDef(attributes.get("grow_def"));
					heroBuilder.setAttack(attributes.get("attack"));
					heroBuilder.setGrowAttack(attributes.get("grow_attack"));
					heroBuilder.setSpeed(attributes.get("speed"));
					heroBuilder.setGrowSpeed(attributes.get("grow_speed"));

					final int[] indexCount = {0};
					skills.forEach((kk, vv) -> {
						HeroSkillProtobuf.HeroSkill heroSkill = HeroSkillProtobuf.HeroSkill
								.newBuilder()
								.setType(kk + "")
								.setLevel(Integer.parseInt(vv + ""))
								.build();
						heroBuilder.setSkills(indexCount[0], heroSkill);
						indexCount[0]++;
					});

					HeroBasicInfoProtobuf.HeroBasicInfo hero = heroBuilder.build();
					reply.write(hero);
				});
			} else if (ReUtil.isMatch("^hero_.*$", type)) {
				awardHeroForMe(redisTemplate, identifier, type, reply, parameter);
			} else if (type.equals("dummy")) {
				log.info("do nothing");
			} else {
				throw new RuntimeException("unknow award type " + type);
			}
		}
		log.info("获得：{} 数量：{}", type, value);

	}

	public static List<JSONObject> refreshNewStoreNowEx(JSONArray table) {
		log.info("refresh_new_store_now_ex");

		long weekOfYear = currentDay();

		List types = new ArrayList<>();

		table.forEach(json -> {
			JSONObject jsonObject = (JSONObject) json;
			types.set(Integer.parseInt(jsonObject.get("type") + ""), jsonObject.get("type"));
		});

		List<JSONObject> items = new ArrayList<>();

		for (int i = 0; i < table.size(); i++) {
			JSONObject currentIndexObj = (JSONObject) table.get(i);
			JSONObject f = (JSONObject) currentIndexObj.get("good_fomula");

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

	public static void refreshStore(RedisTemplate redisTemplate, Integer identifier, Object store, Integer type) {
		Generated generated = new Generated();
		JSONArray rubyConstNewStoreHotTable = generated.getRUBY_CONST_NEW_STORE_HOT_TABLE();
		JSONArray rubyConstNewStoreDiscountTable = generated.getRUBY_CONST_NEW_STORE_DISCOUNT_TABLE();
		JSONArray rubyConstNewStoreTimeLimitTable = generated.getRUBY_CONST_NEW_STORE_TIME_LIMIT_TABLE();
		JSONArray tables = new JSONArray();
		tables.add(rubyConstNewStoreHotTable);
		tables.add(rubyConstNewStoreDiscountTable);
		tables.add(rubyConstNewStoreTimeLimitTable);
		if (type >= 1 && type <= 3) {
			JSONArray table = (JSONArray) tables.get(type - 1);
			List<JSONObject> items = refreshNewStoreNowEx(table);

			Map h = new HashMap();

			for (int i = 0; i < items.size(); i++) {
				h.put((i + 1) + "", items.get(i));
			}

			redisTemplate.opsForHash().putAll("u:" + identifier + ":store:" + type, h);

			if (store != null) {
				for (int i = 0; i < items.size(); i++) {
					JSONObject item = items.get(i);
					StoreItemProtobuf.StoreItem storeItem = StoreItemProtobuf.StoreItem
							.newBuilder()
							.setName(item.get("name") + "")
							.setNumber(Integer.parseInt(item.get("number") + ""))
							.setCostType(item.get("cost_type") + "")
							.setCostNumber(Integer.parseInt(item.get("cost_number") + ""))
							.build();
					// TODO: 2021/9/1  源代码是添加商品
					//store.goods.push(g)
				}
			}

		} else {
			log.info("unknow store type");
		}
	}

}
