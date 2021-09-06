package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("skill_script_skill")
public class SkillScriptSkill {
	/**
	 * 主键
	 */
	@TableField(value = "id")
	private String id;

	/**
	 * 
	 */
	@TableField("description")
	private String description;

	/**
	 * 
	 */
	@TableField("effect")
	private String effect;

	/**
	 * 
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 
	 */
	@TableField("action")
	private String action;

	/**
	 * 
	 */
	@TableField("levels")
	private String levels;

	/**
	 * 
	 */
	@TableField("logic")
	private String logic;

	/**
	 * 
	 */
	@TableField("name")
	private String name;


}