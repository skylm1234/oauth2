package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("in_game_purchase")
public class InGamePurchase {
/**
	 * 主键
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 
	 */
	@TableField("stone")
	private Integer stone;

	/**
	 * 
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 
	 */
	@TableField("cost")
	private Integer cost;


}