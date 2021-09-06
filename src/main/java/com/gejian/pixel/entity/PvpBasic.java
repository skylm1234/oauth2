package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("pvp_basic")
public class PvpBasic {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	@TableField("range_min")
	private Integer rangeMin;

	/**
	 * 
	 */
	@TableField("range_max")
	private Integer rangeMax;

	/**
	 * 
	 */
	@TableField("range")
	private String range;


}