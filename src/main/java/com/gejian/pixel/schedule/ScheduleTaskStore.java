package com.gejian.pixel.schedule;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		Integer maxPlayerId = NumberUtil.parseInt(redisTemplate.opsForValue().get("user:max:player_identifier")+"");
		for (int identifier = 1; identifier < maxPlayerId; identifier++) {
			if (redisTemplate.hasKey("u:"+identifier+":items")) {
				log.info("refreshing "+identifier+"'s store  ... ");
				for (int i = 0; i < tables.size(); i++) {
					redisTemplate.delete("u:"+identifier+":store:"+(i+1));
					Map<String, String> items = new HashMap();

					JSONArray storesJSONArray = JSONUtil.parseArray(stores.get(i));
					JSONArray storeJSONArray = JSONUtil.parseArray(storesJSONArray.get(0).toString());
					for (int j = 0; j < storeJSONArray.size(); j++) {
						JSONArray storeRandomJSONArray = JSONUtil.parseArray(storesJSONArray.get(RandomUtil.randomInt(storesJSONArray.size())));
						items.put(String.valueOf(j+1), JSONUtil.toJsonStr(storeRandomJSONArray.get(j)));
					}
					redisTemplate.opsForHash().putAll("u:"+identifier+":store:"+(i+1), items);
				}
			}
		}
	}
}
