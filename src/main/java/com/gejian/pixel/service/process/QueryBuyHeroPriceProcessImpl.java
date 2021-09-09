package com.gejian.pixel.service.process;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.entity.BuyHero;
import com.gejian.pixel.proto.CommQueryBuyHeroPriceRequestProtobuf;
import com.gejian.pixel.proto.CommQueryBuyHeroPriceResponseProtobuf;
import com.gejian.pixel.service.BuyHeroService;
import com.gejian.pixel.service.Process;
import com.gejian.pixel.utils.Helper;
import com.gejian.pixel.utils.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private BuyHeroService buyHeroService;

	@Override
	public CommQueryBuyHeroPriceResponseProtobuf.CommQueryBuyHeroPriceResponse doProcess(CommQueryBuyHeroPriceRequestProtobuf.CommQueryBuyHeroPriceRequest request) throws Exception {
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
			List<BuyHero> buyHeroes = buyHeroService.list();
			if (buyHeroes!=null) {
				BuyHero buyHero = buyHeroes.get(x - 1);
				Integer cooldown = buyHero.getCooldown();

				if (cooldown!=0 && Helper.currentTimestamp()-items.get("buy_hero_"+x+"_timestamp") >= cooldown) {
					prices.put("buy_hero_"+x+"_price", 0);
				}else {
					double fomula = buyHeroService.calculation(x - 1, 0);
					prices.put("buy_hero_"+x+"_price", NumberUtil.parseInt(fomula+""));
				}
			}
		}
		responseBuilder.addPrices(prices.get("buy_hero_1_price"));
		responseBuilder.addPrices(prices.get("buy_hero_2_price"));
		responseBuilder.addPrices(prices.get("buy_hero_3_price"));

		log.info("{}",responseBuilder.getPricesList());

		redisTemplate.opsForHash().putAll("u:"+identifier+":items", items);

		responseBuilder.setRequest(request);

		return responseBuilder.build();
	}
}
