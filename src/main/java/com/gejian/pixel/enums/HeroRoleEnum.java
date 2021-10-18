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
public enum HeroRoleEnum implements TwoConstructEnums{

	/** 战士 **/
	ZHAN_SHI(1,"战士"),

	/** 刺客 **/
	CI_KE(2,"刺客"),

	/** 辅助 **/
	FU_ZHU(4,"辅助");

	private Integer code;
	private String type;

	private static final Map<Integer,HeroRoleEnum> VALUE_MAP = new HashMap<>();

	static {
		for(HeroRoleEnum heroRoleEnum : HeroRoleEnum.values()){
			VALUE_MAP.put(heroRoleEnum.code,heroRoleEnum);
		}
	}

	@JsonCreator
	public static HeroRoleEnum valueOf(Integer code) {
		return VALUE_MAP.get(code);
	}

}
