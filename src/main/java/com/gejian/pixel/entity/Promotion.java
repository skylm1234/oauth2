package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value ="promotion")
public class Promotion implements Serializable {
	/**
	 * 主键
	 */
	@TableId
	private Integer id;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	@TableField("`key`")
	private String key;

	/**
	 * 
	 */
	private Integer type;

	/**
	 * 
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 
	 */
	private Integer compareType;

	/**
	 * 
	 */
	private Integer parameter;

	/**
	 * 
	 */
	private Integer precondition;

	/**
	 * 
	 */
	private Integer next;

	/**
	 * 
	 */
	private String award;

	/**
	 * 
	 */
	private String percent;


}