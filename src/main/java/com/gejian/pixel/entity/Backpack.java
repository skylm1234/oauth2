package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("backpack")
public class Backpack {
	/**
	 * 
	 */
	@TableField("level")
	private String level;

	/**
	 * 
	 */
	@TableField("gold_max")
	private Integer goldMax;

	/**
	 * 
	 */
	@TableField("exp_max")
	private Integer expMax;

	/**
	 * 
	 */
	@TableField("item_max")
	private Integer itemMax;

	/**
	 * 
	 */
	@TableField("fomula")
	private String fomula;

	/**
	 * 
	 */
	@TableField("prerequests")
	private String prerequests;


}