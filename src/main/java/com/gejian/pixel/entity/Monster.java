package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("monster")
public class Monster {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("model")
	private String model;

	/**
	 * 
	 */
	@TableField("color")
	private String color;

	/**
	 * 
	 */
	@TableField("skill")
	private String skill;


}