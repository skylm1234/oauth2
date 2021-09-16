package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("buy_hero")
public class BuyHero {
	/**
	 * 
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 
	 */
	@TableField("dropid")
	private String dropid;

	/**
	 * 
	 */
	@TableField("award")
	private String award;

	/**
	 * 
	 */
	@TableField("consume")
	private String consume;

	/**
	 * 
	 */
	@TableField("fomula")
	private String fomula;

	/**
	 * 
	 */
	@TableField("amount")
	private String amount;

	@TableField("amount_script")
	private String amountScript;

	/**
	 * 
	 */
	@TableField("cooldown")
	private Integer cooldown;

	/**
	 * 
	 */
	@TableField("day_limit")
	private Integer dayLimit;

	/**
	 * 
	 */
	@TableField("chips")
	private Integer chips;


}