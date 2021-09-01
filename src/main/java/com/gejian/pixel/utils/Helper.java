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
	public static  String stringValue(RedisTemplate redisTemplate, Integer identifier, String name){
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
			reply.channel().writeAndFlush(messageBase);
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
	public static Integer itemCount(RedisTemplate redisTemplate, Integer identifier, String name){
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
			reply.channel().writeAndFlush(messageBase);
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
	public static Boolean increaseItemValue(RedisTemplate redisTemplate, Integer identifier, String name, Integer delta, ChannelHandlerContext reply){
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
				reply.channel().writeAndFlush(messageBase);
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
				reply.channel().writeAndFlush(messageBase);
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
	public static Boolean decreaseItemValue(RedisTemplate redisTemplate, Integer identifier, String name, Integer delta, ChannelHandlerContext reply){
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
	 *
	 * @param ar Object集合
	 * @return
	 */
	public static Integer selectFromMultipleAward(List<Object> ar){

		for (int i = 0; i < ar.size(); i++) {
			if (i != 0) {
				Integer[] temp = (Integer[]) ar.get(i);
				temp[1] = temp[1] + temp[i-1];
			}
		}
		Integer factor = 0;
		Integer[] temp2 = (Integer[]) ar.get(ar.size() - 1);
		if (temp2[1] < 100) {
			factor = RandomUtil.randomInt(100);
		}else {
			factor = RandomUtil.randomInt(temp2[1]);
		}
		for (int i = 0; i < ar.size(); i++) {
			Integer[] temp3 = (Integer[]) ar.get(i);
			if (factor<=temp3[1]){
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

	public static void awardHeroForMe(RedisTemplate redisTemplate, Integer identifier, String type, ChannelHandlerContext reply, Integer parameter){
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
							reply.channel().writeAndFlush(t);
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
							reply.channel().writeAndFlush(t);
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
			reply.channel().writeAndFlush(item);
		}
	}

	public static void promotionFoo(String key, Integer parameter, RedisTemplate redisTemplate, Integer identifier, ChannelHandlerContext reply){
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

	public static void onNotifyEventOfPromotions(RedisTemplate redisTemplate, String key, Integer parameter, Integer identifier, ChannelHandlerContext reply){
		promotionFoo(key, parameter, redisTemplate, identifier, reply);
	}

	public static long currentTimestamp(){
		return System.currentTimeMillis()/1000 + 28800;
	}

	public static long currentDay(){
		return (System.currentTimeMillis()/1000 + 28800) / 24 / 3600;
	}

	public static void updateRanklistHonor(RedisTemplate redisTemplate, Integer identifier, ChannelHandlerContext reply) {
		Long omyrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor",hexEncode(stringValue(redisTemplate, identifier,"nickname")));
		__update_ranklist(redisTemplate, identifier, "honor", itemCount(redisTemplate, identifier, "total_honor"));

		Long myrank = redisTemplate.opsForZSet().reverseRank("ranklist:honor",hexEncode(stringValue(redisTemplate, identifier,"nickname")));
		if (omyrank != myrank && myrank <= 100 && hexEncode(stringValue(redisTemplate, identifier, "nickname"))!=null) {
			boardcaseWorldEvent("PWBC快讯：祝贺玩家<color=red>"+stringValue(redisTemplate, identifier, "nickname")+"</color>荣誉榜提升至第"+(myrank+1)+"名！");
		}
		onNotifyEventOfPromotions(redisTemplate,"kingofpvp", Integer.valueOf((myrank+1)+""), identifier, reply);

	}

	public static void __update_ranklist(RedisTemplate redisTemplate, Integer identifier, String ranklist, Integer score) {
		redisTemplate.opsForZSet().add("ranklist:"+ranklist, score, Integer.parseInt(hexEncode(stringValue(redisTemplate, identifier,"nickname"))));
	}

	//获取奖励
	public static void getAward(RedisTemplate redisTemplate, Integer identifier, List<Object> ar, ChannelHandlerContext reply, Boolean store2backpack, String parameter) {
		Generated generated = new Generated();

		String type = (String) ar.get(0);

		String[] arIndex3Arr = (String[]) ar.get(3);
		if (arIndex3Arr.length > 1) {
			type = arIndex3Arr[RandomUtil.randomInt(arIndex3Arr.length)];
		}else {
			type = arIndex3Arr[0];
		}

		int value = 0;
		Object[] arIndex2Arr = (Object[]) ar.get(2);
		if (arIndex2Arr.length <= 1) {
			value = Integer.parseInt(arIndex2Arr[0]+"");
		}else {
			value = RandomUtil.randomInt(Integer.parseInt(arIndex2Arr[1]+"")) + Integer.parseInt(arIndex2Arr[0]+"");
		}

		if (store2backpack!=null) {
			if (store2backpack) {
				if (ReUtil.isMatch("^box_monry_.*$", type) ||
						ReUtil.isMatch("^box_exp_book_.*$", type) ||
						ReUtil.isMatch("^box_private_soulchip_.*$", type) ||
						ReUtil.isMatch("^box_skillbook_.*$", type) ||
						ReUtil.isMatch("^box_stone_.*$", type)) {
					generated.dropItems(type, identifier, reply, store2backpack, parameter);
				}
			}else if ((type.equals("gold") ||
					type.equals("exp") ||
					type.equals("stone") ||
					type.equals("honer") ||
					ReUtil.isMatch("^exp_book_.*$", type)) ||
					ReUtil.isMatch("^private_soulchip_.*$", type) ||
					ReUtil.isMatch("^book_skill_.*$", type)) {
				Map<String,Integer> pack = redisTemplate.opsForHash().entries("u:"+identifier+":temp_backpack");
				if (pack!=null){
					pack.forEach((k,v)->{
						pack.put(k,v);
					});
				}
				pack.put("level", pack.get("level")-1);

				if (type.equals("gold")){
					//金币增量
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer goldMax = (Integer) jsonObject.get("gold_max");
					if (redisTemplate.opsForHash().increment("u:"+identifier+":temp_backpack_items",type,value) > goldMax) {
						redisTemplate.opsForHash().put("u:"+identifier+":temp_backpack_items", type, goldMax);
					}
				}else if (type.equals("exp")){
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer expMax = (Integer) jsonObject.get("exp_max");
					if (redisTemplate.opsForHash().increment("u:"+identifier+":temp_backpack_items",type,value) > expMax) {
						redisTemplate.opsForHash().put("u:"+identifier+":temp_backpack_items", type, expMax);
					}
				}else {
					JSONArray rubyConstBackpackTable = generated.getRUBY_CONST_BACKPACK_TABLE();
					JSONObject jsonObject = (JSONObject) rubyConstBackpackTable.get(pack.get("level"));
					Integer itemMax = (Integer) jsonObject.get("item_max");
					if (redisTemplate.opsForHash().size("u:"+identifier+":temp_backpack_items") > itemMax+2) {
						redisTemplate.opsForHash().increment("u:"+identifier+":temp_backpack_items", type, value);
					}else {
						log.info("exceed temp backpack max");
					}
				}
			}else if(ReUtil.isMatch("^hero_.*$", type)) {
				log.info("can not handle award hero");
			}else if (type.equals("dummy")) {
				log.info("do nothing");
			}else {
				log.info("unknow award type {}",type);
			}
		}else {
			if (ReUtil.isMatch("^box_monry_.*$", type) ||
					ReUtil.isMatch("^box_exp_book_.*$", type) ||
					ReUtil.isMatch("^box_private_soulchip_.*$", type) ||
					ReUtil.isMatch("^box_skillbook_.*$", type) ||
					ReUtil.isMatch("^box_stone_.*$", type)) {
				generated.dropItems(type, identifier, reply, store2backpack, parameter);
			}else if ((type.equals("gold") ||
					type.equals("stone") ||
					type.equals("honor") ||
					ReUtil.isMatch("^exp_book_.*$", type)) ||
					ReUtil.isMatch("^private_soulchip_.*$", type) ||
					ReUtil.isMatch("^book_skill_.*$", type)) {
				increaseItemValue(redisTemplate, identifier, type, value, reply);
				if (type.equals("honor")) {
					if (parameter!=null && parameter.equals("archives")) {
					}else {
						increaseItemValue(redisTemplate, identifier, type, value, reply);
						updateRanklistHonor(redisTemplate, identifier, reply);
						onNotifyEventOfPromotions(redisTemplate, "maxhonor", value, identifier, reply);
					}
				}

				if (ReUtil.isMatch("^exp_book_.*$", type)) {
					onNotifyEventOfPromotions(redisTemplate, "expbooks", value, identifier, reply);
				}else if (type.equals("gold")) {
					onNotifyEventOfPromotions(redisTemplate, "maxgold", value, identifier, reply);
				}else {}
			}else if (type.equals("exp")) {
				//上阵角色都加经验
				Long nbHerosInTeam = redisTemplate.opsForHash().size("u:"+identifier+":teams");
				Map<String,Object> teams = redisTemplate.opsForHash().entries("u:"+identifier+":heros");
				teams.forEach((k,v)->{
					/*
					attributes = redis.hgetall("u:#{identifier}:#{k}:attributes")
					skills = redis.hgetall("u:#{identifier}:#{k}:skills")

					# hero = Hash.new

					attributes.each do |k, v|
						attributes[k] = k == 'type' ? v : v.to_i
					end

					# puts v.to_i, nb_heros_in_team
					attributes['exp'] += v.to_i / nb_heros_in_team

					while attributes['level'] < 99 do

						item = RUBY_CONST_LEVEL_UPGRADE_TABLE[attributes['level']]
						exp_need = item["start#{attributes['star']}"]
						if attributes['exp'] >= exp_need then

							attributes['exp'] -= exp_need #(hero:exp() - exp_need)
							attributes['level'] += 1 #(hero:level() + 1)
							attributes['hp'] += attributes['grow_hp'] # (hero:hp() + hero:grow_hp())
							attributes['def'] += attributes['grow_def'] # (hero:def() + hero:grow_def())
							attributes['attack'] += attributes['grow_attack'] # (hero:attack() + hero:grow_attack())
							attributes['speed'] += attributes['grow_speed'] # (hero:speed() + hero:grow_speed())

							attributes['exp'] = exp_need if attributes['level'] == 99
						else
							break
						end
					end
					redis.mapped_hmset("u:#{identifier}:#{k}:attributes", attributes)


					hero = HERO_BASIC_INFO.new(attributes)
					 */
					Map<String,Integer> attributes = redisTemplate.opsForHash().entries("u:"+identifier+":"+k+":attributes");
					Map skills = redisTemplate.opsForHash().entries("u:"+identifier+":"+k+":skills");

					attributes.forEach((k1,v1)->{
						attributes.put(k1,k1.equals("type")?v1:Integer.parseInt(v1+""));
					});

					attributes.put("exp", attributes.get("exp") + (Integer.parseInt(v + "") / Integer.parseInt(nbHerosInTeam + "")));

					while (attributes.get("level") < 99) {
						JSONArray rubyConstLevelUpgradeTable = generated.getRUBY_CONST_LEVEL_UPGRADE_TABLE();
						Map<String,Integer> item = (Map) rubyConstLevelUpgradeTable.get(attributes.get("level"));
						Integer expNeed = item.get("start" + attributes.get("start"));
						if (attributes.get("exp") >= expNeed) {
							//attributes.put("exp", attributes.get("exp")-=expNeed);
							//attributes.put("level",)
						}
					}
				});
			}
		}

	}

}
