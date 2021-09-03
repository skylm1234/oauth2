package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("pvp_refresh")
public class PvpRefresh {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("battle_times_each_refresh")
	private Integer battleTimesEachRefresh;

	/**
	 * 
	 */
	@TableField("refresh_time")
	private Integer refreshTime;

	/**
	 * 
	 */
	@TableField("consume_fomula")
	private String consumeFomula;

	/**
	 * 
	 */
	@TableField("consume")
	private String consume;


}