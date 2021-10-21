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
public enum StoreCurrencyTypeEnum {
	/**
	 * 0: stone-钻石  1：honor-荣耀  2：gold-金币
	 */
	STONE(0,"stone"),
	HONOR(1,"honor"),
	GOLD(2,"gold");




	private Integer code;
	private String type;

	private static final Map<Integer, StoreCurrencyTypeEnum> VALUE_MAP = new HashMap<>();

	static {
		for(StoreCurrencyTypeEnum storeTypeEnum : StoreCurrencyTypeEnum.values()){
			VALUE_MAP.put(storeTypeEnum.code,storeTypeEnum);
		}
	}

	@JsonCreator
	public static StoreCurrencyTypeEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

}
