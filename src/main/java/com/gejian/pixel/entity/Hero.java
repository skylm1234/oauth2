package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("hero")
public class Hero {
/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 名称
	 */
	@TableField("name")
	private String name;

	/**
	 * 类型 1战士 2刺客 4辅助
	 */
	@TableField("type")
	private Integer type;

	/**
	 * 描述
	 */
	@TableField("`desc`")
	private String desc;

	/**
	 * 肤色
	 */
	@TableField("color")
	private Integer color;

	/**
	 * 碎片
	 */
	@TableField("chips")
	private Integer chips;

	/**
	 * 
	 */
	@TableField("alive")
	private Integer alive;

	/**
	 * 血量
	 */
	@TableField("hp")
	private Long hp;

	/**
	 * 攻击力
	 */
	@TableField("attack")
	private Long attack;

	/**
	 * 防御
	 */
	@TableField("defense")
	private Long defense;

	/**
	 * 速度
	 */
	@TableField("speed")
	private Long speed;

	/**
	 * 升级增加血量
	 */
	@TableField("hp_upgrade")
	private Long hpUpgrade;

	/**
	 * 升级增加攻击力
	 */
	@TableField("attack_upgrade")
	private Long attackUpgrade;

	/**
	 * 升级增加防御
	 */
	@TableField("defense_upgrade")
	private Long defenseUpgrade;

	/**
	 * 升级增加速度
	 */
	@TableField("speed_upgrade")
	private Long speedUpgrade;

	/**
	 * 创建时间
	 */
	@TableField("create_time")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@TableField("update_time")
	private LocalDateTime updateTime;

	/**
	 * 技能
	 */
	@TableField("skill")
	private String skill;

	/**
	 * 升星
	 */
	@TableField("star_upgrade")
	private String starUpgrade;

	/**
	 * 分组1
	 */
	@TableField("group1")
	private String group1;

	/**
	 * 分组1技能
	 */
	@TableField("group1_skill")
	private String group1Skill;

	/**
	 * 分组2
	 */
	@TableField("group2")
	private String group2;

	/**
	 * 分组2技能
	 */
	@TableField("group2_skill")
	private String group2Skill;

	/**
	 * 平a技能
	 */
	@TableField("skill_a")
	private String skillA;

	/**
	 * 一技能
	 */
	@TableField("skill1")
	private String skill1;

	/**
	 * 一技能名称
	 */
	@TableField("skill1_name")
	private String skill1Name;

	/**
	 * 二技能
	 */
	@TableField("skill2")
	private String skill2;

	/**
	 * 二技能名称
	 */
	@TableField("skill2_name")
	private String skill2Name;

	/**
	 * 三技能
	 */
	@TableField("skill3")
	private String skill3;

	/**
	 * 三技能名称
	 */
	@TableField("skill3_name")
	private String skill3Name;

	/**
	 * 四技能
	 */
	@TableField("skill4")
	private String skill4;

	/**
	 * 四技能名称
	 */
	@TableField("skill4_name")
	private String skill4Name;

	/**
	 * 战队
	 */
	@TableField("squad1")
	private String squad1;

	/**
	 * 战队2
	 */
	@TableField("squad2")
	private String squad2;

	/**
	 * 主要属性
	 */
	@TableField("basic")
	private String basic;

	/**
	 * 升级属性
	 */
	@TableField("basic_upgrade")
	private String basicUpgrade;

	@TableField("basic_upgrade_expand")
	private String basicUpgradeExpand;


	@TableField("basic_expand")
	private String basicExpand;

	@TableField("star_upgrade_fomula")
	private String starUpgradeFomula;



}