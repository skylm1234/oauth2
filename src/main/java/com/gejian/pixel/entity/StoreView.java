package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-22 14:31
 * @description：
 */

@TableName("store_view")
@Data
public class StoreView {

	@TableField("id")
	private Long id;

	/**
	 *
	 */
	@TableField("good_fomula")
	private String goodFomula;

	/**
	 *
	 */
	@TableField("items")
	private String items;

	/**
	 *
	 */
	@TableField("`type`")
	private Integer type;

	private String prefix;

	@TableField("`from`")
	private int from;

	@TableField("`to`")
	private int to;
}
