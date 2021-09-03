package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("pvp_award")
public class PvpAward {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("vectory_times")
	private Integer vectoryTimes;

	/**
	 * 
	 */
	@TableField("award_fomula")
	private String awardFomula;

	/**
	 * 
	 */
	@TableField("award")
	private String award;


}