package com.gejian.pixel.dto.stage;

import com.gejian.pixel.entity.NewStoreGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuanxue
 * @version 1.0.0
 * @ClassName StorePageDTO.java
 * @Description 商品管理条件入参
 * @createTime 2021-10-19 15:22:00
 */
@Data
@ApiModel("商店管理查询条件")
public class StagePageDTO implements Serializable {
	/**
	 * id
	 */
	@ApiModelProperty("id")
	private Long id;

	/**
	 *
	 */
	@ApiModelProperty("关卡")
	private String name;

	/**
	 *
	 */
	@ApiModelProperty("分类")
	private String classType;

	/**
	 *
	 */
	@ApiModelProperty("")
	private Integer preRequest;

	/**
	 *
	 */
	@ApiModelProperty("难度")
	private Integer mode;

	/**
	 *
	 */
	@ApiModelProperty("图标")
	private String icon;

	/**
	 *
	 */
	@ApiModelProperty("图标变色")
	private String iconColor;

	/**
	 *
	 */
	@ApiModelProperty("背景")
	private String bg;

	/**
	 *
	 */
	@ApiModelProperty("背景变色")
	private String bgColor;

	/**
	 *
	 */
	@ApiModelProperty("推荐战力")
	private Integer recommandPower;

	/**
	 *
	 */
	@ApiModelProperty("小怪id")
	private String monsters;

	/**
	 *
	 */
	@ApiModelProperty("小怪属性")
	private String monstersAttributes;

	/**
	 *
	 */
	@ApiModelProperty("boss")
	private String boss;

	/**
	 *
	 */
	@ApiModelProperty("boss属性")
	private String bossAttributes;

	/**
	 *
	 */
	@ApiModelProperty("怪物最大数量")
	private Integer monstersMax;

	/**
	 *
	 */
	@ApiModelProperty("goblin_fomula")
	private String goblinFomula;

	/**
	 *
	 */
	@ApiModelProperty("goblin击杀掉落奖励")
	private String goblin;

	/**
	 *
	 */
	@ApiModelProperty("basic_award_fomula")
	private String basicAwardFomula;

	/**
	 *
	 */
	@ApiModelProperty("关卡基础收益")
	private String basicAward;

	/**
	 *
	 */
	@ApiModelProperty("monsters_killed_award_fomula")
	private String monstersKilledAwardFomula;

	/**
	 *
	 */
	@ApiModelProperty("击杀小怪收益")
	private String monstersKilledAward;

	/**
	 *
	 */
	@ApiModelProperty("boss_award_fomula")
	private String bossAwardFomula;

	/**
	 *
	 */
	@ApiModelProperty("击杀boss收益")
	private String bossAward;

	/**
	 *
	 */
	@ApiModelProperty("波数")
	private Integer wave;

	/**
	 *
	 */
	@ApiModelProperty("小怪技能等级")
	private String monsterSkillLevel;

	/**
	 *
	 */
	@ApiModelProperty("boss技能等级")
	private String bossSkillLevel;
}
