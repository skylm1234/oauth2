package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.util.Date;
import java.math.BigDecimal;

@Data
@TableName("skill_upgrade")
public class SkillUpgrade {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 技能id
	 */
	@TableField("skill_id")
	private String skillId;

	/**
	 * 技能等级
	 */
	@TableField("level")
	private String level;

	/**
	 * 金币消耗
	 */
	@TableField("gold")
	private Integer gold;

	/**
	 * 技能书消耗
	 */
	@TableField("book")
	private Integer book;


}