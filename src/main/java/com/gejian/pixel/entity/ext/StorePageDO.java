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
	private Long id;

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
	private String tableName;

	/**
	 *
	 */
	private Integer places;

	/**
	 *
	 */
	private Integer type;

}
