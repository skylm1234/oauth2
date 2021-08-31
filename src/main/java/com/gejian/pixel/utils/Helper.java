package com.gejian.pixel.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gejian.pixel.constants.Generated;
import com.gejian.pixel.proto.*;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.lang.model.util.AbstractAnnotationValueVisitor6;
import java.util.HashMap;
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

	private void heroNameToSkillBookHash(){
		JSONArray rubyConstHeroTable = generated.getRUBY_CONST_HERO_TABLE();
		if (rubyConstHeroTable!=null){
			for (Object o : rubyConstHeroTable) {
				JSONObject constHero = (JSONObject) o;
				JSONObject hero = new JSONObject();
				hero.putOnce("skilla",constHero.get("name"));
				hero.putOnce("skill1",constHero.get("name"));
				hero.putOnce("skill2",constHero.get("name"));
				hero.putOnce("skill3",constHero.get("name"));
				hero.putOnce("skill4",constHero.get("name"));
				skillToHero.add(hero);
			}
		}
	}

	@PostConstruct
	public void init(){
		heroNameToSkillBookHash();
	}

	/**
	 * 字符串转换成十六进制字符串
	 * @param s 需要转换的字符串
	 * @return 十六进制字符串
	 */
	public static String hexEncode(String s){
		return HexUtil.encodeHexStr(s.getBytes());
	}

	/**
	 * 将十六进制字符数组转换为字符串，默认编码UTF-8
	 * @param hexStr 十六进制字符串
	 * @return 字符串
	 */
	public static String hexDecode(String hexStr){
		return HexUtil.decodeHexStr(hexStr);
	}

	/**
	 * 生成礼物背包标识符
	 * @param redisTemplate redis
	 * @return 背包标识符
	 */
	public static String giftbagIdentifier(RedisTemplate redisTemplate){
		return StrFormatter.format("giftbag_identifier_{}",redisTemplate.opsForValue().increment("user:max:giftbag_identifier"));
	}

	/**
	 * 生成用户标识符
	 * @param redisTemplate redis
	 * @return 用户标识符
	 */
	public static String generateUserIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:player_identifier") + "";
	}

	/**
	 * 生成英雄标识符
	 * @param redisTemplate redis
	 * @return 英雄标识符
	 */
	public static String generateHeroIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:hero_identifier") + "";
	}

	/**
	 * 生成IAP标识符
	 * @param redisTemplate redis
	 * @return IAP标识符
	 */
	public static String generateIapIdentifier(RedisTemplate redisTemplate){
		return redisTemplate.opsForValue().increment("user:max:iap_identifier") + "";
	}

	/**
	 * 得到字符串值
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @return
	 */
	public static  String stringValue(RedisTemplate redisTemplate, String identifier, String name){
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":strings", name);
		if (result!=null){
			return hexDecode(result.toString());
		}
		return null;
	}

	/**
	 * 得到字符串值
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param value 值
	 * @param reply 回复的消息
	 * @return
	 */
	public static PlayerStringProtobuf.PlayerString setStringValue(RedisTemplate redisTemplate, String identifier, String name, String value, ChannelHandlerContext reply){
		redisTemplate.opsForHash().put("u:" + identifier + ":strings", name, hexEncode(value));
		PlayerStringProtobuf.PlayerString item = PlayerStringProtobuf.PlayerString
				.newBuilder()
				.setKey(name)
				.setValue(value)
				.build();
		if (reply!=null){
			MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
					.newBuilder()
					.setData(item.toByteString())
					.build();
			reply.channel().write(messageBase);
		}

		return item;
	}

	/**
	 * 获得物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @return
	 */
	public static Integer itemCount(RedisTemplate redisTemplate, String identifier, String name){
		Object result = redisTemplate.opsForHash().get("u:" + identifier + ":items", name);
		if (result!=null){
			return Integer.valueOf(result+"");
		}
		return 0;
	}

	/**
	 * 设置物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param delta 值
	 * @param reply 回复的消息
	 */
	public static void setItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta, ChannelHandlerContext reply){
		redisTemplate.opsForHash().put("u:" + identifier + ":items", name, delta);
		if (reply!=null){
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(name)
					.setValue(delta)
					.build();
			MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
					.newBuilder()
					.setData(item.toByteString())
					.build();
			reply.channel().write(messageBase);
		}
	}

	/**
	 * 增加物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param delta 值
	 * @param reply 回复的消息
	 * @return
	 */
	public static Boolean increaseItemValue(RedisTemplate redisTemplate, String identifier, String name, Integer delta, ChannelHandlerContext reply){
		if (delta>0) {
			Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			long current = Long.parseLong(result+"");
			if (reply!=null){
				PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
						.newBuilder()
						.setKey(name)
						.setValue(current)
						.build();
				MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
						.newBuilder()
						.setData(item.toByteString())
						.build();
				reply.channel().write(messageBase);
			}
		}else if (delta<0){
			Object result = redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta);
			long current = Long.parseLong(result+"");
			if (current<0){
				redisTemplate.opsForHash().increment("u:" + identifier + ":items", name, delta * -1);
				return Boolean.FALSE;
			}else {
				PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
						.newBuilder()
						.setKey(name)
						.setValue(current)
						.build();
				MessageBaseProtobuf.MessageBase messageBase = MessageBaseProtobuf.MessageBase
						.newBuilder()
						.setData(item.toByteString())
						.build();
				reply.channel().write(messageBase);
			}
		}
		return Boolean.TRUE;
	}

	/**
	 * 减少物品个数
	 * @param redisTemplate redis
	 * @param identifier 标识符
	 * @param name 名称
	 * @param delta 值
	 * @param reply 回复的消息
	 * @return
	 */
	public static Boolean decrease_item_value(RedisTemplate redisTemplate, String identifier, String name, Integer delta, ChannelHandlerContext reply){
		return increaseItemValue(redisTemplate, identifier, name, delta, reply);
	}

	/**
	 *
	 * @param ar 数组
	 * @return
	 */
	public static Integer selectFromMultipleAward(Integer[][] ar){
		for (int i = 0; i < ar.length; i++) {
			if (i != 0){
				ar[i][1] = ar[i][1] + ar[i-1][1];
			}
		}
		Integer factor = 0;
		if (ar[ar.length - 1][1] < 100){
			factor = RandomUtil.randomInt(100);
		}else {
			factor = RandomUtil.randomInt(ar[ar.length - 1][1]);
		}
		for (int i = 0; i < ar.length; i++) {
			if (factor<=ar[i][1]){
				return i;
			}
		}
		return null;
	}

	/**
	 * 世界广播时间
	 * @param desc 内容
	 */
	public static void boardcaseWorldEvent(String desc){
		log.info("{} boardcast_world_event => {}",DateUtil.now(),desc);
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

	public static void awardHeroForMe(RedisTemplate redisTemplate, String identifier, String type, ChannelHandlerContext reply, Integer parameter){
		boolean r = ReUtil.isMatch("^hero_(\\d+)$", type);
		if (r) {
			String id = type.split("_")[1];

			// TODO: 2021/8/31 后面要替换
			//String[][] record = RUBY_CONST_HERO_TABLE_HASH["X#{id}"]
			Map<String, Map> record = null;

			if (redisTemplate.opsForHash().putIfAbsent("u:" +identifier+ ":heros", type, 1)) {
				onNotifyEventOfPromotions(redisTemplate, "mostheros", 1, identifier, reply);
				HeroBasicInfoProtobuf.HeroBasicInfo.Builder heroBuilder = HeroBasicInfoProtobuf.HeroBasicInfo
						.newBuilder()
						.setId(Integer.parseInt(generateHeroIdentifier(redisTemplate)))
						.setType(type)
						.setLevel(1)    // 抽出来都是1级
						.setExp(0)        // 无经验
						.setStar(0);// 0星

				Float rate = 1.0f;

				if (parameter!=null) {
					if (parameter == 3) {
						// TODO: 2021/8/31 后面要替换
						// rate = RUBY_CONST_QUALITY_UPGRADE_RATE_TABLE[1]['up'].to_f
						rate = 1.0f;
					}
				}

				heroBuilder.setQuality(parameter!=null && parameter==3 ? 2 : 1);
				heroBuilder.setGrowHp(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("hp") * rate) + ""));
				heroBuilder.setHp(Integer.parseInt(((Float) record.get("basic_expand").get("hp") * rate) + ""));
				heroBuilder.setGrowDef(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("defense") * rate) + ""));
				heroBuilder.setDef(Integer.parseInt(((Float) record.get("basic_expand").get("defense") * rate) + ""));
				heroBuilder.setGrowAttack(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("attack") * rate) + ""));
				heroBuilder.setAttack(Integer.parseInt(((Float) record.get("basic_expand").get("attack") * rate) + ""));
				heroBuilder.setGrowSpeed(Integer.parseInt(((Float) record.get("basic_upgrade_expand").get("speed") * rate) + ""));
				heroBuilder.setSpeed(Integer.parseInt(((Float) record.get("basic_expand").get("speed") * rate) + ""));
				heroBuilder.setNumber(1);

				Long teams = redisTemplate.opsForHash().size("u:" +identifier+ ":teams");
				if (teams < 5) {

					redisTemplate.opsForHash().put("u:" +identifier+ ":teams", type, teams+1);

					if (reply != null) {
						Map tb = redisTemplate.opsForHash().entries("u:" +identifier+ ":teams");
						tb.forEach((k,v)->{
							PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
									.newBuilder()
									.setKey(k+"")
									.setValue(Long.parseLong(v+""))
									.build();
							reply.channel().write(t);
						});
					}
				}
				Long teams_pvp = redisTemplate.opsForHash().size("u:" +identifier+ ":teams_pvp");
				if (teams_pvp < 5) {
					redisTemplate.opsForHash().put("u:" +identifier+ ":teams_pvp", type, teams_pvp+1);

					if (reply != null) {
						Map tb = redisTemplate.opsForHash().entries("u:" +identifier+ ":teams_pvp");
						tb.forEach((k,v)->{
							PlayerItemProtobuf.PlayerItem t = PlayerItemProtobuf.PlayerItem
									.newBuilder()
									.setKey(k+"")
									.setValue(Long.parseLong(v+""))
									.build();
							reply.channel().write(t);
						});
					}
				}
			}

		}else {
			log.info("invalid type");
		}
	}

	public static void appedArchivesToReply(ChannelHandlerContext reply, String key, long value){
		if (reply!=null){
			PlayerItemProtobuf.PlayerItem item = PlayerItemProtobuf.PlayerItem
					.newBuilder()
					.setKey(key)
					.setValue(value)
					.build();
			reply.channel().write(item);
		}
	}

	public static void promotionFoo(String key, Integer parameter, RedisTemplate redisTemplate, String identifier, ChannelHandlerContext reply){
		Object now = null;
		Map map = null;
		switch (key){
			case "type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_1_monster_kill", parameter);
				appedArchivesToReply(reply, "type_1_monster_kill", Long.parseLong(now+""));
				break;
			case "type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_2_monster_kill", parameter);
				appedArchivesToReply(reply, "type_2_monster_kill", Long.parseLong(now+""));
				break;
			case "type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "type_3_monster_kill", parameter);
				appedArchivesToReply(reply, "type_3monster_kill", Long.parseLong(now+""));
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
				appedArchivesToReply(reply, "cuthand", Long.parseLong(now+""));
				break;
			case "mostheros":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mostheros", parameter);
				appedArchivesToReply(reply, "mostheros", Long.parseLong(now+""));
				break;
			case "mosthire":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "mosthire", parameter);
				appedArchivesToReply(reply, "mosthire", Long.parseLong(now+""));
				break;
			case "kingofpvp":
				Map archives = (HashMap) redisTemplate.opsForHash().get("u:" + identifier + ":archives", "kingofpvp");
				log.info("{}",archives);
				log.info("{} => {}",identifier, parameter);
				if (archives!=null){
					if (archives.get("kingofpvp")==null){
						archives.put("kingofpvp", parameter);
						redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
						appedArchivesToReply(reply, "kingofpvp", parameter);
					}else {
						if (archives.get("kingofpvp")!=null && parameter<=Integer.parseInt(archives.get("kingofpvp")+"")){
							archives.put("kingofpvp",parameter);
							redisTemplate.opsForHash().putAll("u:" + identifier + ":archives", archives);
							appedArchivesToReply(reply, "kingofpvp", parameter);
						}
					}
				}
				break;
			case "tempbackpack":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "tempbackpack", parameter);
				appedArchivesToReply(reply, "tempbackpack", Long.parseLong(now+""));
				break;
			case "expbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "expbooks", parameter);
				appedArchivesToReply(reply, "expbooks", Long.parseLong(now+""));
				break;
			case "consumeexpbooks":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "consumeexpbooks", parameter);
				appedArchivesToReply(reply, "consumeexpbooks", Long.parseLong(now+""));
				break;
			case "maxgold":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxgold", parameter);
				appedArchivesToReply(reply, "maxgold", Long.parseLong(now+""));
				break;
			case "maxhonor":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxhonor", parameter);
				appedArchivesToReply(reply, "maxhonor", Long.parseLong(now+""));
				break;
			case "maxtopskills":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "maxtopskills", parameter);
				appedArchivesToReply(reply, "maxtopskills", Long.parseLong(now+""));
				break;
			case "daily_skill_upgrade":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_skill_upgrade", parameter);
				appedArchivesToReply(reply, "daily_skill_upgrade", Long.parseLong(now+""));
				break;
			case "daily_pvp_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_monster_kill", Long.parseLong(now+""));
				break;
			case "daily_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_pvp_times", parameter);
				appedArchivesToReply(reply, "daily_pvp_times", Long.parseLong(now+""));
				break;
			case "daily_exp_book_consume":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_exp_book_consume", parameter);
				appedArchivesToReply(reply, "daily_exp_book_consume", Long.parseLong(now+""));
				break;
			case "daily_buy_hero":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_buy_hero", parameter);
				appedArchivesToReply(reply, "daily_buy_hero", Long.parseLong(now+""));
				break;
			case "daily_type_1_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_1_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_1_monster_kill", Long.parseLong(now+""));
				break;
			case "daily_type_2_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_2_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_2_monster_kill", Long.parseLong(now+""));
				break;
			case "daily_type_3_monster_kill":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_type_3_monster_kill", parameter);
				appedArchivesToReply(reply, "daily_type_3_monster_kill", Long.parseLong(now+""));
				break;
			case "daily_store_buy_times":
				now = redisTemplate.opsForHash().increment("u:" + identifier + ":archives", "daily_store_buy_times", parameter);
				appedArchivesToReply(reply, "daily_store_buy_times", Long.parseLong(now+""));
				break;
			default:
				break;
		}
	}

	public static void onNotifyEventOfPromotions(RedisTemplate redisTemplate, String key, Integer parameter, String identifier, ChannelHandlerContext reply){
		promotionFoo(key, parameter, redisTemplate, identifier, reply);
	}

	public static long currentTimestamp(){
		return System.currentTimeMillis()/1000 + 28800;
	}

	public static long currentDay(){
		return (System.currentTimeMillis()/1000 + 28800) / 24 / 3600;
	}

}
