package com.gejian.pixel.service.process;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf;
import com.gejian.pixel.proto.CommQueryBuyHeroPriceResponseProtobuf;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljb
 * @date 2021年09月02日 16:56
 * @description 查询购买英雄价格
 */
@Service(CommandConstants.QUERY_BUY_HERO_PRICE)
@Slf4j
@RequiredArgsConstructor
public class QueryBuyHeroPriceProcessImpl implements Process<CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest,
		CommQueryBuyHeroPriceResponseProtobuf.CommQueryBuyHeroPriceResponse> {

	private final RedisTemplate redisTemplate;

	private Generated generated = new Generated();

	@Override
	public CommQueryBuyHeroPriceResponseProtobuf.CommQueryBuyHeroPriceResponse doProcess(CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest commQueryBuyHeroPriceRequest) throws Exception {
		CommQueryBuyHeroPriceResponseProtobuf.CommQueryBuyHeroPriceResponse.Builder responseBuilder = CommQueryBuyHeroPriceResponseProtobuf
				.CommQueryBuyHeroPriceResponse.newBuilder();

		Integer identifier = UserHolder.get().getIdentifier();

		List<String> itemsKey = Arrays.asList("buy_hero_1_times",
				"buy_hero_1_timestamp", "buy_hero_2_times",
				"buy_hero_2_timestamp", "buy_hero_3_times",
				"buy_hero_3_timestamp");

		List itemsValue = redisTemplate.opsForHash().multiGet("u:" + identifier + ":items", itemsKey);
		Map<String,Integer> items = new HashMap<>();
		for (int i = 0; i < itemsKey.size(); i++) {
			items.put(itemsKey.get(i), Integer.parseInt(itemsValue.get(i)+""));
		}

		long currentDay = Helper.currentDay();

		Map<String,Integer> prices = new HashMap<>();

		for (int x = 1; x <= 3; x++) {
			JSONArray rubyConstBuyHeroTable = generated.getRUBY_CONST_BUY_HERO_TABLE();
			if (rubyConstBuyHeroTable!=null) {
				JSONObject heroObj = (JSONObject) rubyConstBuyHeroTable.get(x - 1);
				Integer cooldown = (Integer) heroObj.get("cooldown");

				if (cooldown!=0 && Helper.currentTimestamp()-items.get("buy_hero_"+x+"_timestamp") >= cooldown) {
					prices.put("buy_hero_"+identifier+"_price", 0);
				}else {
					// TODO: 2021/9/2 不清楚call用法
					//prices['buy_hero_%d_price' % x] = RUBY_CONST_BUY_HERO_TABLE[x-1]['fomula'].call(items['buy_hero_%d_times' % x])
					Integer fomula = (Integer) heroObj.get("fomula");
					prices.put("buy_hero_"+x+"_price",fomula);
				}
			}
		}
		responseBuilder.addPrices(prices.get("buy_hero_1_price"));
		responseBuilder.addPrices(prices.get("buy_hero_2_price"));
		responseBuilder.addPrices(prices.get("buy_hero_3_price"));

		log.info("{}",responseBuilder.getPricesList());

		redisTemplate.opsForHash().putAll("u:"+identifier+":items", items);

		return responseBuilder.build();
	}
}
