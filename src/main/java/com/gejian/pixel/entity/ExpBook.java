package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("exp_book")
public class ExpBook {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 简介
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 经验值
	 */
	@TableField("value")
	private Integer value;

	/**
	 * 颜色
	 */
	@TableField("quality")
	private String quality;

	/**
	 * 图标
	 */
	@TableField("icon")
	private String icon;


}