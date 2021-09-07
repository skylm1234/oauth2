package com.gejian.pixel.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
import com.gejian.pixel.service.NewStoreDiscountService;
import com.gejian.pixel.service.NewStoreHotService;
import com.gejian.pixel.service.NewStoreService;
import com.gejian.pixel.service.NewStoreTimeLimitService;
import com.gejian.pixel.service.impl.NewStoreTimeLimitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Reese
 * @date 2021/9/2
 */
@Component
public class StoreHelper {

	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private NewStoreHotService newStoreHotService;
	@Autowired
	private NewStoreDiscountService newStoreDiscountService;
	@Autowired
	private NewStoreTimeLimitService newStoreTimeLimitService;

	/**
	 * 刷新商店
	 */
	public void refreshStore(Integer identifier, CommRefreshStoreRequestProtobuf.CommRefreshStoreRequest request,
							 CommRefreshStoreResponseProtobuf.CommRefreshStoreResponse.Builder builder) {
		PlayerStoreProtobuf.PlayerStore playerStore = PlayerStoreProtobuf.PlayerStore.newBuilder().build();
		this.refresh(identifier, playerStore.getGoods0List(), 1);
		this.refresh(identifier, playerStore.getGoods1List(), 2);
		this.refresh(identifier, playerStore.getGoods2List(), 3);
		builder.setStore(playerStore);
	}

	/**
	 * 购买商店物品
	 */
	public void buyStoreItem(Integer identifier, CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest request,
							 CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse.Builder builder) {
		// 获取商品数据
		List<Integer> tb = this.getTB();

		int type = request.getType();

		int index = request.getIndex();

		if (type >= 1 && type <= tb.size() && index <= tb.get(type - 1)) {

			String storeKey = getStoreKey(identifier, type);

			Map items = this.redisTemplate.opsForHash().entries(storeKey);

			this.buyStoreItemHelper(identifier, request, builder, items);

			PlayerItemProtobuf.PlayerItem playerItem = Helper.onNotifyEventOfPromotions(redisTemplate, "cuthand", 1, identifier);
			builder.addItems(playerItem);

			PlayerItemProtobuf.PlayerItem playerItem1 = Helper.onNotifyEventOfPromotions(redisTemplate, "daily_store_buy_times", 1, identifier);
			builder.addItems(playerItem1);

		} else {
			builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER_STORE_ITEM);
		}
	}

	private void buyStoreItemHelper(Integer identifier, CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest request,
									CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse.Builder builder, Map items) {

		String index = this.parseString(items.get(String.valueOf(request.getIndex())));
		if (index == null) {
			builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER);
			return;
		}

		JSONObject json = JSONUtil.parseObj(index);

		String[] array = {"name", "number", "cost_type", "cost_number"};

		Map<String, Object> item = new HashMap<>();

		for (String s : array) {
			item.put(s, json.get(s));
		}

		String expected = request.getExpected();
		String itemName = this.parseString(item.get("name"));

		if (!expected.equals(itemName)) {
			builder.setResult(ErrorEnum.ERROR_STORE_ALREADY_REFRESHED);
			return;
		}

		if (this.parseInt(item.get("number")) < request.getNumber()) {
			builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
			return;
		}

		String costType = this.parseString(item.get("cost_type"));
		Integer costTypeCount = Helper.itemCount(redisTemplate, identifier, costType);
		Long costNumber = this.parseLong(item.get("cost_number")) * request.getNumber();
		if (costTypeCount < costNumber) {
			builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
			return;
		}

		PlayerItemProtobuf.PlayerItem playerItem = Helper.decreaseItemValue(redisTemplate, identifier, costType, costNumber);
		builder.addItems(playerItem);

		if (itemName.startsWith("hero_")) {

			PlayerInfoProtobuf.PlayerInfo.Builder playerInfoBuilder = PlayerInfoProtobuf.PlayerInfo.newBuilder();

			for (int i = 1; i < request.getNumber(); i++) {
				Helper.awardHeroForMe(redisTemplate, identifier, itemName, playerInfoBuilder, null);
			}

			builder.addAllHeros(playerInfoBuilder.getHerosList());
			builder.addAllItems(playerInfoBuilder.getItemsList());
			builder.addAllTeams(playerInfoBuilder.getTeamsList());
			builder.addAllArchives(playerInfoBuilder.getArchivesList());
			builder.addAllTeamsPvp(playerInfoBuilder.getTeamsPvpList());

		} else if (itemName.startsWith("gold_")) {

			Long amount = Long.parseLong(itemName.substring("gold_".length()));

			PlayerItemProtobuf.PlayerItem playerItem1 = Helper.increaseItemValue(redisTemplate, identifier, "gold", amount * request.getNumber());
			builder.addItems(playerItem1);

		} else if (itemName.startsWith("giftbag_")) {

			Long count = Long.parseLong(itemName.substring("giftbag_".length()));

			String[] ar = {"小礼品袋", "礼品袋", "大礼品袋", "超级礼品袋", "超级礼品袋"};
			String[] ac = {"giftbag_0", "giftbag_1", "giftbag_2", "giftbag_3", "giftbag_4"};

			Map gb = new HashMap();
			gb.put("identifier", Helper.giftbagIdentifier(redisTemplate));
			gb.put("icon", Helper.hexEncode("gift_package.png"));
			gb.put("desc", Helper.hexEncode(ar[(int) (count / 250)]));
			gb.put("action", ac[(int) (count / 250)]);

			this.redisTemplate.opsForHash().putAll(this.getGiftBagKey(identifier, this.parseString(gb.get("identifier"))), gb);

			PlayerItemProtobuf.PlayerItem playerItem1 = Helper.increaseItemValue(redisTemplate, identifier, "giftbags", 1L);
			builder.addItems(playerItem1);

			this.redisTemplate.opsForHash().put(this.getGiftBagsKey(identifier), this.parseString(gb.get("identifier")), gb.get("desc"));

		} else {
			PlayerItemProtobuf.PlayerItem playerItem1 = Helper.increaseItemValue(redisTemplate, identifier, itemName, (long) request.getNumber());
			builder.addItems(playerItem1);
		}

		item.put("number", this.parseInt(item.get("number")) - request.getNumber());
		this.redisTemplate.opsForHash().put(this.getStoreKey(identifier, request.getType()), request.getIndex(), JSONUtil.toJsonStr(item));
	}

	private List<Integer> getTB() {
		// tb = [RUBY_CONST_NEW_STORE_HOT_TABLE, RUBY_CONST_NEW_STORE_DISCOUNT_TABLE, RUBY_CONST_NEW_STORE_TIME_LIMIT_TABLE]
		List<Integer> list = new ArrayList<>();
		list.add(this.newStoreHotService.count());
		list.add(this.newStoreDiscountService.count());
		list.add(this.newStoreTimeLimitService.count());
		return list;
	}

	private void refresh(Integer identifier, List<StoreItemProtobuf.StoreItem> list, int type) {
		String storeKey = getStoreKey(identifier, type);

		Map entries = this.redisTemplate.opsForHash().entries(storeKey);

		entries.forEach((k, v) -> {

			JSONObject json = JSONUtil.parseObj(v);

			list.add(StoreItemProtobuf.StoreItem.newBuilder()
					.setName(this.parseString(json.getStr("name")))
					.setNumber(this.parseInt(json.getInt("number")))
					.setCostType(this.parseString(json.getStr("cost_type")))
					.setCostNumber(this.parseInt(json.getInt("cost_number")))
					.build());
		});
	}

	private String getStoreKey(Integer identifier, int type) {
		return "u:" + identifier.toString() + ":store:" + type;
	}

	private String getGiftBagKey(Integer identifier, String type) {
		return "u:" + identifier.toString() + ":giftbag:" + type;
	}

	private String getGiftBagsKey(Integer identifier) {
		return "u:" + identifier.toString() + ":giftbags";
	}

	public Long parseLong(Object o) {
		return o == null ? 0L : Long.parseLong(o.toString());
	}

	public Integer parseInt(Object o) {
		return this.parseLong(o).intValue();
	}

	public String parseString(Object o) {
		return o == null ? "" : o.toString();
	}

}
