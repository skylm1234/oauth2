package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("vip")
public class Vip {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	@TableField("level")
	private Integer level;

	/**
	 * 
	 */
	@TableField("charge")
	private Integer charge;

	/**
	 * 
	 */
	@TableField("itemid")
	private String itemid;

	/**
	 * 
	 */
	@TableField("award")
	private String award;

	/**
	 * 
	 */
	@TableField("chanllege")
	private Integer chanllege;

	/**
	 * 
	 */
	@TableField("backpack_max")
	private Integer backpackMax;

	/**
	 * 
	 */
	@TableField("freerefresh_times_stone")
	private Integer freerefreshTimesStone;

	/**
	 * 
	 */
	@TableField("freerefresh_times_honor")
	private Integer freerefreshTimesHonor;

	/**
	 * 
	 */
	@TableField("freerefresh_times_gold")
	private Integer freerefreshTimesGold;

	/**
	 * 
	 */
	@TableField("timeval_offline_kill_monster_rate")
	private Integer timevalOfflineKillMonsterRate;

	/**
	 * 
	 */
	@TableField("skip_timeval")
	private Integer skipTimeval;

	/**
	 * 
	 */
	@TableField("`des`")
	private String des;

	/**
	 * 
	 */
	@TableField("tianti")
	private Integer tianti;

	/**
	 * 
	 */
	@TableField("tianti_reset")
	private Integer tiantiReset;


}