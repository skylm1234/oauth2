package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("skill_script_role")
public class SkillScriptRole {
	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("attack")
	private Integer attack;

	/**
	 * 
	 */
	@TableField("attack01_audio")
	private String attack01Audio;

	/**
	 * 
	 */
	@TableField("attack02_audio")
	private String attack02Audio;

	/**
	 * 
	 */
	@TableField("attack03_audio")
	private String attack03Audio;

	/**
	 * 
	 */
	@TableField("crit")
	private Integer crit;

	/**
	 * 
	 */
	@TableField("death_audio")
	private String deathAudio;

	/**
	 * 
	 */
	@TableField("defense")
	private Integer defense;

	/**
	 * 
	 */
	@TableField("dodge")
	private Integer dodge;

	/**
	 * 
	 */
	@TableField("hit_audio")
	private String hitAudio;

	/**
	 * 
	 */
	@TableField("hp")
	private Integer hp;

	/**
	 * 
	 */
	@TableField("normalSkill")
	private String normalSkill;

	/**
	 * 
	 */
	@TableField("skills")
	private String skills;

	/**
	 * 
	 */
	@TableField("speed")
	private Integer speed;

	private String type;


}