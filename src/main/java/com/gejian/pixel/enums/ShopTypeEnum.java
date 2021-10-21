package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuanxue
 * @Description
 * @createTime 2021年10月18日 17:18:00
 */
@AllArgsConstructor
@Getter
public enum ShopTypeEnum {
	/**
	 * 1:折扣 2：热卖 3：限时
	 */
	NEW_STORE_DISCOUNT(1,"new_store_discount"),
	NEW_STORE_HOT(2,"new_store_hot"),
	NEW_STORE_TIME_LIMIT(3,"new_store_time_limit");



	private Integer code;
	private String type;

	private static final Map<Integer, ShopTypeEnum> VALUE_MAP = new HashMap<>();
	private static final Map<String, ShopTypeEnum> TYPE_MAP = new HashMap<>();

	static {
		for(ShopTypeEnum storeTypeEnum : ShopTypeEnum.values()){
			VALUE_MAP.put(storeTypeEnum.code,storeTypeEnum);
		}
		for(ShopTypeEnum storeTypeEnum : ShopTypeEnum.values()){
			TYPE_MAP.put(storeTypeEnum.type,storeTypeEnum);
		}
	}

	@JsonCreator
	public static ShopTypeEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}
	@JsonCreator
	public static ShopTypeEnum valueOfByType(String type) {
		return TYPE_MAP.get(type);
	}

}
