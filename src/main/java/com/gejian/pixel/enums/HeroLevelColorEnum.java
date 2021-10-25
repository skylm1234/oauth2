package com.gejian.pixel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 16:45
 * @description：
 */

@AllArgsConstructor
@Getter
public enum HeroLevelColorEnum implements CodeToJsonEnums {

	/** 绿色 **/
	GREEN(1,"绿色"),

	/** 蓝色 **/
	BLUE(2,"蓝色"),

	/** 紫色 **/
	PURPLE(3,"紫色"),

	/** 橙色 **/
	ORANGE(4,"橙色");
	private static final Map<Integer,HeroLevelColorEnum> VALUE_MAP = new HashMap<>();

	private Integer code;
	private String type;

	static {
		for(HeroLevelColorEnum heroLevelColorEnum : HeroLevelColorEnum.values()){
			VALUE_MAP.put(heroLevelColorEnum.code,heroLevelColorEnum);
		}
	}

	@JsonCreator
	public static HeroLevelColorEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

}
