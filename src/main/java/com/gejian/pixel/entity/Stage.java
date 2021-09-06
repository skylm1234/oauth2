package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("stage")
public class Stage {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("class_type")
	private Integer classType;

	/**
	 * 
	 */
	@TableField("prerequest")
	private Integer prerequest;

	/**
	 * 
	 */
	@TableField("mode")
	private Integer mode;

	/**
	 * 
	 */
	@TableField("icon")
	private String icon;

	/**
	 * 
	 */
	@TableField("icon_color")
	private String iconColor;

	/**
	 * 
	 */
	@TableField("bg")
	private String bg;

	/**
	 * 
	 */
	@TableField("bg_color")
	private String bgColor;

	/**
	 * 
	 */
	@TableField("recommand_power")
	private Integer recommandPower;

	/**
	 * 
	 */
	@TableField("monsters")
	private String monsters;

	/**
	 * 
	 */
	@TableField("monsters_attributes")
	private String monstersAttributes;

	/**
	 * 
	 */
	@TableField("boss")
	private String boss;

	/**
	 * 
	 */
	@TableField("boss_attributes")
	private String bossAttributes;

	/**
	 * 
	 */
	@TableField("monsters_max")
	private Integer monstersMax;

	/**
	 * 
	 */
	@TableField("goblin_fomula")
	private String goblinFomula;

	/**
	 * 
	 */
	@TableField("goblin")
	private String goblin;

	/**
	 * 
	 */
	@TableField("basic_award_fomula")
	private String basicAwardFomula;

	/**
	 * 
	 */
	@TableField("basic_award")
	private String basicAward;

	/**
	 * 
	 */
	@TableField("monsters_killed_award_fomula")
	private String monstersKilledAwardFomula;

	/**
	 * 
	 */
	@TableField("monsters_killed_award")
	private String monstersKilledAward;

	/**
	 * 
	 */
	@TableField("boss_award_fomula")
	private String bossAwardFomula;

	/**
	 * 
	 */
	@TableField("boss_award")
	private String bossAward;

	/**
	 * 
	 */
	@TableField("wave")
	private Integer wave;

	/**
	 * 
	 */
	@TableField("monster_skill_level")
	private String monsterSkillLevel;

	/**
	 * 
	 */
	@TableField("boss_skill_level")
	private String bossSkillLevel;


}