package com.gejian.pixel.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.entity.PvpAward;
import com.gejian.pixel.proto.CommWorldEventUpdateProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.service.PvpAwardService;
import com.gejian.pixel.utils.BroadcastUtil;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ljb
 * @date 2021年09月10日 11:50
 * @description PVP竞技场刷新	2个小时刷新一次
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleTaskPvpBattleSettle {

	private final RedisTemplate redisTemplate;

	private final PvpAwardService pvpAwardService;

	@Scheduled(cron = "0 0 */2 * * ?")
	public void refreshPvpBattleSettle(){
		scheduleTaskPvpBattleSettle();
		CommWorldEventUpdateProtobuf.CommWorldEventUpdate event = CommWorldEventUpdateProtobuf.CommWorldEventUpdate
				.newBuilder()
				.setType(1)
				.setInfo("竞技场已经刷新")
				.setFoo("\\0\\0\\0\\0")
				.setBar("\\0\\0\\0")
				.build();
		MessageBaseProtobuf.MessageBase base = MessageBaseProtobuf.MessageBase
				.newBuilder()
				.setName("COMM_WORLD_EVENT_UPDATE")
				.setData(event.toByteString())
				.build();
		BroadcastUtil.broadcast(base);
		log.info("竞技场已经刷新,当前时间:{}", DateUtil.now());
	}

	public void scheduleTaskPvpBattleSettle(){
		Map<Integer, PvpAward> awardHash = pvpAwardService.getHash();
		Integer maxPlayerId = NumberUtil.parseInt(redisTemplate.opsForValue().get("user:max:player_identifier")+"");

		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		//用户是否存在集合
		List existsList = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			for (int identifier = 1; identifier <= maxPlayerId; identifier++) {
				connection.keyCommands().exists(serializer.serialize("u:" + identifier + ":items"));
			}
			return null;
		});

		List<byte[]> itemsKeys = Arrays.asList(
				serializer.serialize("pvp_1_vectory"),
				serializer.serialize("pvp_3_vectory"),
				serializer.serialize("pvp_9_vectory"),
				serializer.serialize("pvp_vectory_times"),
				serializer.serialize("pvp_challage_times"),
				serializer.serialize("should_refresh_pvp_chanllege_ranklist"));

		Map<byte[], Map<byte[], byte[]>> allUserItems = new HashMap<>();

		List allUserItemsValues = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {

			for (int i = 0; i < existsList.size(); i++) {
				if (BooleanUtil.toBoolean(existsList.get(i).toString())) {
					Integer identifier = i + 1;
					connection.hashCommands().hMGet(serializer.serialize("u:" + identifier + ":items"),
							serializer.serialize("pvp_1_vectory"),
							serializer.serialize("pvp_3_vectory"),
							serializer.serialize("pvp_9_vectory"),
							serializer.serialize("pvp_vectory_times"),
							serializer.serialize("pvp_challage_times"),
							serializer.serialize("should_refresh_pvp_chanllege_ranklist"));

				}
			}

			return null;
		});

		redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			//初始化一个下标用来计数，来获取到对应用户的在管道中的items数据
			int userIndex = 0;

			//所有用户的物品奖励数据
			Map<String,Map<byte[],byte[]>> allUserAwardGiftbags = new HashMap<>();
			Map<String,Map<byte[],byte[]>> allUserAwardGiftbagDesc = new HashMap<>();

			for (int i = 0; i < existsList.size(); i++) {
				if (BooleanUtil.toBoolean(existsList.get(i).toString())) {
					Integer identifier = i + 1;
					List<Integer> itemsValue = (List<Integer>) allUserItemsValues.get(userIndex);

					Map<String, Integer> items = new HashMap<>();

					for (int j = 0; j < itemsKeys.size(); j++) {
						String key = serializer.deserialize(itemsKeys.get(j));
						String value = String.valueOf(itemsValue.get(j));
						if (value==null) {
							Map<byte[],byte[]> initDataMap = new HashMap();
							if (key.equals("should_refresh_pvp_chanllege_ranklist")) {
								//该状态初始值为1,其他默认是0
								value = "1";
							}else {
								value = "0";
							}
							initDataMap.put(serializer.serialize(key), serializer.serialize(value));
							connection.hashCommands().hMSet(serializer.serialize("u:"+identifier+":items"), initDataMap);
						}
						items.put(key, NumberUtil.parseInt(value));
					}

					if (items.get("pvp_vectory_times")>0) {
						if (items.get("pvp_vectory_times")>=9) {
							PvpAward x3 = awardHash.get(3);
							JSONObject awardFomulaObj = JSONUtil.parseObj(x3.getAwardFomula());
							//当前用户的物品奖励数据
							//生成礼包ID
							String giftbagIdentifier = Helper.giftbagIdentifier(redisTemplate);
							Map<byte[],byte[]> userAwardGiftbags = getAwardGiftbags(serializer,
									giftbagIdentifier,
									"PVP战场礼包（高级）",
									items.get("pvp_9_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")),
									"gift_package.png");
							Map<byte[],byte[]> userAwardGiftbagDesc = getAwardGiftbagDesc(serializer,giftbagIdentifier,"PVP战场礼包（高级）");
							allUserAwardGiftbags.put(StrFormatter.format("u:{}:giftbag:{}", String.valueOf(identifier), giftbagIdentifier),userAwardGiftbags);
							allUserAwardGiftbagDesc.put(StrFormatter.format("u:{}:giftbags", String.valueOf(identifier)), userAwardGiftbagDesc);
							//增加胜利次数
							items.put("pvp_9_vectory", items.get("pvp_9_vectory")+1);
						}else if (items.get("pvp_vectory_times")>=3) {
							PvpAward x2 = awardHash.get(2);
							JSONObject awardFomulaObj = JSONUtil.parseObj(x2.getAwardFomula());
							//当前用户的物品奖励数据
							//生成礼包ID
							String giftbagIdentifier = Helper.giftbagIdentifier(redisTemplate);
							Map<byte[],byte[]> userAwardGiftbags = getAwardGiftbags(serializer,
									giftbagIdentifier,
									"PVP战场礼包（中级）",
									items.get("pvp_3_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")),
									"gift_package.png");
							Map<byte[],byte[]> userAwardGiftbagDesc = getAwardGiftbagDesc(serializer,giftbagIdentifier,"PVP战场礼包（中级）");
							allUserAwardGiftbags.put(StrFormatter.format("u:{}:giftbag:{}", String.valueOf(identifier), giftbagIdentifier),userAwardGiftbags);
							allUserAwardGiftbagDesc.put(StrFormatter.format("u:{}:giftbags", String.valueOf(identifier)), userAwardGiftbagDesc);
							//增加胜利次数
							items.put("pvp_3_vectory", items.get("pvp_3_vectory")+1);
						}else if (items.get("pvp_vectory_times")>=1) {
							PvpAward x1 = awardHash.get(1);
							JSONObject awardFomulaObj = JSONUtil.parseObj(x1.getAwardFomula());
							//当前用户的物品奖励数据
							//生成礼包ID
							String giftbagIdentifier = Helper.giftbagIdentifier(redisTemplate);
							Map<byte[],byte[]> userAwardGiftbags = getAwardGiftbags(serializer,
									giftbagIdentifier,
									"PVP战场礼包（初级）",
									items.get("pvp_1_vectory")>0?String.valueOf(awardFomulaObj.get("other")):String.valueOf(awardFomulaObj.get("firstofday")),
									"gift_package.png");
							Map<byte[],byte[]> userAwardGiftbagDesc = getAwardGiftbagDesc(serializer,giftbagIdentifier,"PVP战场礼包（初级）");
							allUserAwardGiftbags.put(StrFormatter.format("u:{}:giftbag:{}", String.valueOf(identifier), giftbagIdentifier),userAwardGiftbags);
							allUserAwardGiftbagDesc.put(StrFormatter.format("u:{}:giftbags", String.valueOf(identifier)), userAwardGiftbagDesc);
							//增加胜利次数
							items.put("pvp_1_vectory", items.get("pvp_1_vectory")+1);
						}
					}

					//添加奖励及奖励描述
					allUserAwardGiftbags.forEach((k,v)->{
						connection.hashCommands().hMSet(serializer.serialize(k), v);
					});
					allUserAwardGiftbagDesc.forEach((k,v)->{
						connection.hashCommands().hMSet(serializer.serialize(k), v);
					});

					items.put("pvp_vectory_times", 0);
					items.put("pvp_challage_times", 0);
					items.put("should_refresh_pvp_chanllege_ranklist", 1);

					Map<byte[], byte[]> serializerItems = new HashMap<>();
					items.forEach((k,v)-> serializerItems.put(serializer.serialize(k), serializer.serialize(String.valueOf(v))));

					//充值各种次数
					connection.hashCommands().hMSet(serializer.serialize("u:"+identifier+":items"), serializerItems);

					userIndex++;
				}
			}

			return null;
		});

	}

	private Map<byte[],byte[]> getAwardGiftbags(RedisSerializer<String> serializer, String giftbagIdentifier, String desc, String action, String icon){
		Map<byte[],byte[]> giftbags = new HashMap<>();
		giftbags.put(serializer.serialize("identifier"), serializer.serialize(giftbagIdentifier));
		giftbags.put(serializer.serialize("icon"), serializer.serialize(Helper.hexEncode(icon)));
		giftbags.put(serializer.serialize("desc"), serializer.serialize(Helper.hexEncode(desc)));
		giftbags.put(serializer.serialize("action"), serializer.serialize(action));
		return giftbags;
	}

	private Map<byte[],byte[]> getAwardGiftbagDesc(RedisSerializer<String> serializer, String giftbagIdentifier,String desc){
		Map<byte[],byte[]> giftbagsDesc = new HashMap<>();
		giftbagsDesc.put(serializer.serialize(giftbagIdentifier),serializer.serialize(desc));
		return giftbagsDesc;
	}

}
