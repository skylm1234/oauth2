package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("skill")
public class Skill {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private String id;

	/**
	 * 
	 */
	@TableField("name")
	private String name;

	/**
	 * 
	 */
	@TableField("skill_icon")
	private String skillIcon;

	/**
	 * 
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 
	 */
	@TableField("active_consume_fomula")
	private String activeConsumeFomula;

	/**
	 * 
	 */
	@TableField("active_consume")
	private String activeConsume;

	@TableField("upgrade_consume_fomula")
	private String upgradeConsumeFomula;

	/**
	 * 
	 */
	@TableField("upgrade_consume")
	private String upgradeConsume;

	@TableField("cooldown")
	private String cooldown;

	/**
	 * 
	 */
	@TableField("levelvalue")
	private String levelvalue;

	/**
	 * 
	 */
	@TableField("logic")
	private String logic;

	/**
	 * 
	 */
	@TableField("selecter")
	private String selecter;


}