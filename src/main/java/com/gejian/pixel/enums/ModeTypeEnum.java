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
public enum ModeTypeEnum implements CodeToJsonEnums{
	/**
	 * 1:普通 2：噩梦 3：折磨
	 */
	ORDINARY(1,"ordinary"),
	NIGHTMARE(2,"nightmare"),
	TORMENT(3,"torment");



	private Integer code;
	private String type;

	private static final Map<Integer, ModeTypeEnum> VALUE_MAP = new HashMap<>();

	static {
		for(ModeTypeEnum storeTypeEnum : ModeTypeEnum.values()){
			VALUE_MAP.put(storeTypeEnum.code,storeTypeEnum);
		}
	}

	@JsonCreator
	public static ModeTypeEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

}
