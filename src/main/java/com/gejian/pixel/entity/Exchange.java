package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("exchange")
public class Exchange {
/**
	 * 主键
	 */
	@TableId(value = "type")
	private Integer type;

	/**
	 * 
	 */
	@TableField("stone")
	private Integer stone;

	/**
	 * 
	 */
	@TableField("`desc`")
	private String desc;


	/**
	 * 
	 */
	@TableField("amount")
	private Integer amount;


}