package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("star_upgrade")
public class StarUpgrade {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	@TableField("star")
	private Integer star;

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