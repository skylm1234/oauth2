package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("promotion_type_name")
public class PromotionTypeName {
	/**
	 * 
	 */
	@TableId
	@TableField("type")
	private Integer type;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("q")
	private Integer q;

	/**
	 * 
	 */
	@TableField("`group`")
	private Integer group;

	/**
	 * 
	 */
	@TableField("group_name")
	private String groupName;


}