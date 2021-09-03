package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

@Data
@TableName("level_upgrade")
public class LevelUpgrade {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	@TableField("lv")
	private Integer lv;

	/**
	 * 
	 */
	@TableField("start0")
	private Integer start0;

	/**
	 * 
	 */
	@TableField("start1")
	private Integer start1;

	/**
	 * 
	 */
	@TableField("start2")
	private Integer start2;

	/**
	 * 
	 */
	@TableField("start3")
	private Integer start3;

	/**
	 * 
	 */
	@TableField("start4")
	private Integer start4;

	/**
	 * 
	 */
	@TableField("start5")
	private Integer start5;

	/**
	 * 
	 */
	@TableField("start6")
	private Integer start6;

	/**
	 * 
	 */
	@TableField("start7")
	private Integer start7;

	/**
	 * 
	 */
	@TableField("start8")
	private Integer start8;

	/**
	 * 
	 */
	@TableField("start9")
	private Integer start9;

	/**
	 * 
	 */
	@TableField("start10")
	private Integer start10;

	/**
	 * 
	 */
	@TableField("start11")
	private Integer start11;

	/**
	 * 
	 */
	@TableField("start12")
	private Integer start12;

	/**
	 * 
	 */
	@TableField("start13")
	private Integer start13;

	/**
	 * 
	 */
	@TableField("start14")
	private Integer start14;

	/**
	 * 
	 */
	@TableField("start15")
	private Integer start15;

	/**
	 * 
	 */
	@TableField("start16")
	private Integer start16;

	/**
	 * 
	 */
	@TableField("start17")
	private Integer start17;

	/**
	 * 
	 */
	@TableField("start18")
	private Integer start18;

	/**
	 * 
	 */
	@TableField("start19")
	private Integer start19;

	/**
	 * 
	 */
	@TableField("start20")
	private Integer start20;

	/**
	 * 
	 */
	@TableField("start21")
	private Integer start21;

	/**
	 * 
	 */
	@TableField("start22")
	private Integer start22;

	/**
	 * 
	 */
	@TableField("start23")
	private Integer start23;

	/**
	 * 
	 */
	@TableField("start24")
	private Integer start24;

	/**
	 * 
	 */
	@TableField("start25")
	private Integer start25;


}