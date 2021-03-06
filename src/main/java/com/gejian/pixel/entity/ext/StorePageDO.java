package com.gejian.pixel.entity.ext;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理
 * @createTime 2021-10-19 15:22:00
 */
@Data
public class StorePageDO implements Serializable {
	/**
	 * id
	 */
	private String id;

	/**
	 * items
	 */
	private String items;

	/**
	 * goodFomula
	 */
	private String goodFomula;

	/**
	 * 表名
	 */
	private String prefix;

	/**
	 *
	 */
	private Integer from;

	private Integer to;

	/**
	 *
	 */
	private Integer type;

	private Integer places;

}
