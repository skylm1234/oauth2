package com.gejian.pixel.schedule;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.entity.NewStoreDiscount;
import com.gejian.pixel.entity.NewStoreHot;
import com.gejian.pixel.entity.NewStoreTimeLimit;
import com.gejian.pixel.proto.CommWorldEventUpdateProtobuf;
import com.gejian.pixel.proto.MessageBaseProtobuf;
import com.gejian.pixel.service.NewStoreDiscountService;
import com.gejian.pixel.service.NewStoreHotService;
import com.gejian.pixel.service.NewStoreTimeLimitService;
import com.gejian.pixel.utils.BroadcastUtil;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisKeyCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ljb
 * @date 2021年09月10日 14:58
 * @description 商店刷新  9、12、18、22点刷新
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleTaskStore {

	private final RedisTemplate redisTemplate;

	private final NewStoreHotService newStoreHotService;

	private final NewStoreDiscountService newStoreDiscountService;

	private final NewStoreTimeLimitService newStoreTimeLimitService;

	@Scheduled(cron = "0 0 9,12,18,22 * * ?")
	public void refreshStore(){
		scheduleTaskStore();
		CommWorldEventUpdateProtobuf.CommWorldEventUpdate event = CommWorldEventUpdateProtobuf.CommWorldEventUpdate
				.newBuilder()
				.setType(3)
				.setInfo("商店已刷新")
				.setFoo("\\0\\0\\0\\0")
				.setBar("\\0\\0\\0")
				.build();
		MessageBaseProtobuf.MessageBase base = MessageBaseProtobuf.MessageBase
				.newBuilder()
				.setName("COMM_WORLD_EVENT_UPDATE")
				.setData(event.toByteString())
				.build();
		BroadcastUtil.broadcast(base);
		log.info("商店已刷新,当前时间:{}", DateUtil.now());
	}

	public void scheduleTaskStore(){
		List<NewStoreHot> newStoreHotList = newStoreHotService.list();
		List<NewStoreDiscount> newStoreDiscountList = newStoreDiscountService.list();
		List<NewStoreTimeLimit> newStoreTimeLimitList = newStoreTimeLimitService.list();

		JSONArray newStoreHotJSONArray = JSONUtil.parseArray(newStoreHotList);
		JSONArray newStoreDiscountJSONArray = JSONUtil.parseArray(newStoreDiscountList);
		JSONArray newStoreTimeLimitJSONArray = JSONUtil.parseArray(newStoreTimeLimitList);

		List stores = new ArrayList();

		JSONArray tables = new JSONArray();
		tables.add(newStoreHotJSONArray);
		tables.add(newStoreDiscountJSONArray);
		tables.add(newStoreTimeLimitJSONArray);

		for (int i = 0; i < tables.size(); i++) {
			JSONArray table = JSONUtil.parseArray(tables.get(i));
			List store = new ArrayList();
			for (int j = 0; j < 10; j++) {
				store.add(j,Helper.refreshNewStoreNowEx(table));
			}
			stores.add(store);
		}

		Integer maxPlayerId = NumberUtil.parseInt(redisTemplate.opsForValue().get(RedisKeyConstants.USER_MAX_PLAYER_IDENTIFIER)+"");

		RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
		//用户是否存在集合
		List existsList = redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			for (int identifier = 1; identifier <= maxPlayerId; identifier++) {
				connection.keyCommands().exists(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_ITEMS,identifier)));
			}
			return null;
		});

		redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
			//所有用户刷新后的商店数据
			Map<String,String> allUserStoreData = new HashMap<>();
			for (int i = 0; i < existsList.size(); i++) {
				if (BooleanUtil.toBoolean(existsList.get(i).toString())) {
					Integer identifier = i+1;
					log.info("refreshing "+identifier+"'s store  ... ");
					for (int j = 0; j < tables.size(); j++) {
						connection.del(serializer.serialize(StrFormatter.format(RedisKeyConstants.USER_STORE,identifier,(i+1))));
						Map<String, String> items = new HashMap();

						JSONArray storesJSONArray = JSONUtil.parseArray(stores.get(j));
						JSONArray storeJSONArray = JSONUtil.parseArray(storesJSONArray.get(0).toString());
						for (int k = 0; k < storeJSONArray.size(); k++) {
							JSONArray storeRandomJSONArray = JSONUtil.parseArray(storesJSONArray.get(RandomUtil.randomInt(storesJSONArray.size())));
							items.put(String.valueOf(k+1), JSONUtil.toJsonStr(storeRandomJSONArray.get(k)));
						}
						allUserStoreData.put(StrFormatter.format(RedisKeyConstants.USER_STORE,identifier,(j+1)), JSONUtil.toJsonStr(items));
					}
				}
			}
			allUserStoreData.forEach((k,v)->{
				Map<byte[], byte[]> valueMap = new HashMap<>();
				JSONObject valueJSONObj = JSONUtil.parseObj(v);
				valueJSONObj.forEach((key,value)->{
					valueMap.put(serializer.serialize(key),serializer.serialize(value.toString()));
				});
				connection.hashCommands().hMSet(serializer.serialize(k),valueMap);
			});
			return null;
		});
	}
}
