package com.gejian.pixel.model;

import lombok.Data;

import java.util.List;

/**
 * @author ZhouQiang
 * @date 2021/9/2$
 */
@Data
public class DropItem {

	/**
	 * 物品类型
	 */
	private String type;

	/**
	 * 掉落几率
	 */
	private Integer probability;

	/**
	 * 物品数量
	 */
	private List<Integer> numbers;

	/**
	 * 物品集合
	 */
	private List<String> elements;

}
