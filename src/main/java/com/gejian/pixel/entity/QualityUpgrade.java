package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("quality_upgrade")
public class QualityUpgrade {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("quality")
	private Integer quality;

	/**
	 * 
	 */
	@TableField("desc")
	private String desc;

	/**
	 * 
	 */
	@TableField("consume_expand")
	private String consumeExpand;

	/**
	 * 
	 */
	@TableField("consume")
	private String consume;


}