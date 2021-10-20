package com.gejian.pixel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：lijianghuai
 * @date ：2021-10-19 11:30
 * @description：
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {

	LEFT_MENU("0", "left"),

	/**
	 * 顶部菜单
	 */
	TOP_MENU("2", "top"),

	/**
	 * 按钮
	 */
	BUTTON("1", "button");

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

}
