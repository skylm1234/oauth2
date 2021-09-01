package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangtao
 * @since 2021-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ConstSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

	/**
	 * 名称
	 */
    private String name;

	/**
	 * 类型 1战士 2刺客 4辅助
	 */
    private String type;

    /**
	 *描述
	 */
    private String desc;

    /**
	 *肤色
	 */
    private Integer color;

    /**
	 *碎片
	 */
    private Integer chips;

    private Integer alive;

    /**
	 *血量
	 */
    private Long hp;

    /**
	 *攻击力
	 */
    private Long attack;

    /**
	 *防御
	 */
    private Long defense;

    /**
	 *速度
	 */
    private Long speed;

    /**
	 *升级增加血量
	 */
    private Long hpUpgrade;

    /**
	 *升级增加攻击力
	 */
    private Long attackUpgrade;

    /**
	 *升级增加防御
	 */
    private Long defenseUpgrade;

    /**
	 *升级增加速度
	 */
    private Long speedUpgrade;

    /**
	 *创建时间
	 */
    private LocalDateTime createTime;

    /**
	 *更新时间
	 */
    private LocalDateTime updateTime;

    /**
	 *技能
	 */
    private String skill;

    /**
	 *升星
	 */
    private String starUpgrade;

    /**
	 *分组1
	 */
    private String group1;

    /**
	 *分组1技能
	 */
    private String group1Skill;

    /**
	 *分组2
	 */
    private String group2;

    /**
	 *分组2技能
	 */
    private String group2Skill;

    /**
	 *平a技能
	 */
    private String skillA;

    /**
	 *一技能
	 */
    private String skill1;

    /**
	 *一技能名称
	 */
    private String skill1Name;

    /**
	 *二技能
	 */
    private String skill2;

    /**
	 *二技能名称
	 */
    private String skill2Name;

    /**
	 *三技能
	 */
    private String skill3;

    /**
	 *三技能名称
	 */
    private String skill3Name;

    /**
	 *四技能
	 */
    private String skill4;

    /**
	 *四技能名称
	 */
    private String skill4Name;

    /**
	 *战队
	 */
    private String squad1;

    /**
	 *战队2
	 */
    private String squad2;


}
