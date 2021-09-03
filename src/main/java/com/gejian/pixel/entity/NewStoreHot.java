package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("new_store_hot")
public class NewStoreHot {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 
	 */
	@TableField("places")
	private Integer places;

	/**
	 * 
	 */
	@TableField("good_fomula")
	private String goodFomula;

	/**
	 * 
	 */
	@TableField("items")
	private String items;

	/**
	 * 
	 */
	@TableField("type")
	private Integer type;


}