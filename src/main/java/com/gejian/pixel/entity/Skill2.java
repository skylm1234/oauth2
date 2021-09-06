package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("skill_2")
public class Skill2 {
/**
	 * 主键
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 
	 */
	@TableField("description")
	private String description;

	/**
	 * 
	 */
	@TableField("priority")
	private String priority;

	/**
	 * 
	 */
	@TableField("action")
	private String action;

	/**
	 * 
	 */
	@TableField("effect")
	private String effect;

	@TableField("levels")
	private String levels;

	@TableField("logic")
	private String logic;


}