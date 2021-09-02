package com.gejian.pixel.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.gejian.pixel.enums.ErrorEnum;
import com.gejian.pixel.proto.*;
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
		// TODO 获取商品数据
		List<List<Object>> tb = this.getTB();
		int type = request.getType();
		int index = request.getIndex();
		if (type >= 1 && type <= tb.size() && index <= tb.get(type - 1).size()) {
			String storeKey = getStoreKey(identifier, type);
			Map items = this.redisTemplate.opsForHash().entries(storeKey);
			this.buyStoreItemHelper(identifier, request, builder, items);
			// TODO reply
			Helper.onNotifyEventOfPromotions(redisTemplate, "cuthand", 1, identifier, null);
			// TODO reply
			Helper.onNotifyEventOfPromotions(redisTemplate, "daily_store_buy_times", 1, identifier, null);
		} else {
			builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER_STORE_ITEM);
		}
	}

	private void buyStoreItemHelper(Integer identifier, CommBuyStoreItemRequestProtobuf.CommBuyStoreItemRequest request, CommBuyStoreItemResponseProtobuf.CommBuyStoreItemResponse.Builder builder, Map items) {

		String index = items.get(String.valueOf(request.getIndex())).toString();
		if (index == null) {
			builder.setResult(ErrorEnum.ERROR_INVALID_PARAMETER);
			return;
		}

		JSON json = JSONUtil.parse(index);

		String[] array = {"name", "number", "cost_type", "cost_number"};

		Map item = new HashMap();

		for (String s : array) {
			item.put(s, json.getByPath(s));
		}

		String expected = request.getExpected();
		String itemName = item.get("name").toString();


		if (!expected.equals(itemName)) {
			builder.setResult(ErrorEnum.ERROR_STORE_ALREADY_REFRESHED);
			return;
		}

		if (this.parseInt(item.get("number")) < request.getNumber()) {
			builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
			return;
		}

		Integer costTypeCount = Helper.itemCount(redisTemplate, identifier, item.get("cost_type").toString());
		Long costNumber = this.parseLong(item.get("cost_number")) * request.getNumber();
		if (costTypeCount < costNumber) {
			builder.setResult(ErrorEnum.ERROR_NOT_ENOUGH_RESOURCES);
			return;
		}

		Helper.decreaseItemValue(redisTemplate, identifier, item.get("cost_type").toString(), costNumber);
		if (itemName.startsWith("hero_")) {
			PlayerInfoProtobuf.PlayerInfo.Builder playerInfoBuilder = PlayerInfoProtobuf.PlayerInfo.newBuilder();
			for (int i = 1; i < request.getNumber(); i++) {
				Helper.awardHeroForMe(redisTemplate, identifier, itemName, playerInfoBuilder, null);
			}
			BeanUtil.copyProperties(playerInfoBuilder, builder);
		} else if (itemName.startsWith("gold_")) {
			Long amount = Long.parseLong(itemName.substring("gold_".length()));
			Helper.increaseItemValue(redisTemplate, identifier, "gold", amount * request.getNumber());
		} else if (itemName.startsWith("giftbag_")) {
			Long count = Long.parseLong(itemName.substring("giftbag_".length()));
			String[] ar = {"小礼品袋", "礼品袋", "大礼品袋", "超级礼品袋", "超级礼品袋"};
			String[] ac = {"giftbag_0", "giftbag_1", "giftbag_2", "giftbag_3", "giftbag_4"};

			Map gb = new HashMap();
			gb.put("identifier", Helper.giftbagIdentifier(redisTemplate));
			gb.put("icon", Helper.hexEncode("gift_package.png"));
			gb.put("desc", Helper.hexEncode(ar[(int) (count / 250)]));
			gb.put("action", ac[(int) (count / 250)]);

			this.redisTemplate.opsForHash().putAll(this.getGiftBagKey(identifier, gb.get("identifier").toString()), gb);
			Helper.increaseItemValue(redisTemplate, identifier, "giftbags", 1L);
			this.redisTemplate.opsForHash().put(this.getGiftBagsKey(identifier), gb.get("identifier").toString(), gb.get("desc"));
		} else {
			Helper.increaseItemValue(redisTemplate, identifier, itemName, (long) request.getNumber());
		}

		item.put("number", this.parseInt(item.get("number")) - request.getNumber());
		this.redisTemplate.opsForHash().put(this.getStoreKey(identifier, request.getType()), request.getIndex(), JSONUtil.toJsonStr(item));
	}

	private List<List<Object>> getTB() {
		// TODO tb = [RUBY_CONST_NEW_STORE_HOT_TABLE, RUBY_CONST_NEW_STORE_DISCOUNT_TABLE, RUBY_CONST_NEW_STORE_TIME_LIMIT_TABLE]
		return new ArrayList<>();
	}

	private void refresh(Integer identifier, List<StoreItemProtobuf.StoreItem> list, int type) {
		String storeKey = getStoreKey(identifier, type);
		String[] array = {"name", "number", "cost_type", "cost_number"};

		List<Map> items = new ArrayList<>();

		Map entries = this.redisTemplate.opsForHash().entries(storeKey);
		entries.forEach((k, v) -> {
			JSON json = JSONUtil.parse(v);

			Map h = new HashMap();

			for (String s : array) {
				h.put(s, json.getByPath(s));
			}

			items.add(h);
		});

		items.forEach(o -> list.add(StoreItemProtobuf.StoreItem.newBuilder()
				.setName(o.get("name").toString())
				.setNumber(this.parseInt(o.get("number")))
				.setCostType(o.get("cost_type").toString())
				.setCostNumber(this.parseInt(o.get("cost_number")))
				.build()));
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


}
