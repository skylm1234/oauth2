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
	private String attack;

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
	private String crit;

	/**
	 * 
	 */
	@TableField("death_audio")
	private String deathAudio;

	/**
	 * 
	 */
	@TableField("defense")
	private String defense;

	/**
	 * 
	 */
	@TableField("dodge")
	private String dodge;

	/**
	 * 
	 */
	@TableField("hit_audio")
	private String hitAudio;

	/**
	 * 
	 */
	@TableField("hp")
	private String hp;

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
	private String speed;


}