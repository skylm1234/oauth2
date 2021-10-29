package com.gejian.pixel.dto.stage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author lijianghuai
 */
@Data
@ApiModel("关卡Mode")
public class StageDTO implements Serializable {

	@ApiModelProperty("id")
	private Integer id;

	@NotBlank
	@ApiModelProperty(value = "关卡名",required = true)
	private String name;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "分类",required = true)
	private Integer classType;

	@ApiModelProperty(value = "前置关卡",required = true)
	private Integer prerequest = 0;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "难度",required = true)
	private Integer mode;

	@ApiModelProperty("图标")
	private String icon = "../..";

	@NotBlank
	@ApiModelProperty(value = "图标变色",required = true)
	private String iconColor;

	@NotBlank
	@ApiModelProperty(value = "背景",required = true)
	private String bg;

	@NotBlank
	@ApiModelProperty(value = "背景变色",required = true)
	private String bgColor;


	@NotNull
	@Min(1)
	@ApiModelProperty(value = "推荐战力",required = true)
	private Integer recommandPower;

	@NotNull
	@ApiModelProperty(value = "怪物分类",required = true)
	private List<Integer> monsterTypes;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物最大数量",required = true)
	private Integer monstersMax;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物波数",required = true)
	private Integer wave;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物血量",required = true)
	private Integer monsterHp;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物攻击",required = true)
	private Integer monsterAttack;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物防御",required = true)
	private Integer monsterDefense;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "怪物速度",required = true)
	private Integer monsterSpeed;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "怪物技能1等级",required = true)
	private Integer monsterSkil1Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "怪物技能2等级",required = true)
	private Integer monsterSkil2Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "怪物技能3等级",required = true)
	private Integer monsterSkil3Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "怪物技能4等级",required = true)
	private Integer monsterSkil4Level;

	@NotNull
	@Min(10000)
	@ApiModelProperty(value = "bossId",required = true)
	private Integer bossId;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "boss血量",required = true)
	private Integer bossHp;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "boss攻击",required = true)
	private Integer bossAttack;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "boss防御",required = true)
	private Integer bossDefense;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "boss速度",required = true)
	private Integer bossSpeed;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "boss技能1等级",required = true)
	private Integer bossSkil1Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "boss技能2等级",required = true)
	private Integer bossSkil2Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "boss技能3等级",required = true)
	private Integer bossSkil3Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "boss技能4等级",required = true)
	private Integer bossSkil4Level;

	@NotNull
	@Min(0)
	@ApiModelProperty(value = "goblin血量",required = true)
	private Integer goblinHp;

	@NotBlank
	@ApiModelProperty(value = "goblin掉落",required = true)
	private String goblinDrop;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "基础收益-金币",required = true)
	private Integer basicGoldIncome;

	@NotNull
	@Min(1)
	@ApiModelProperty(value = "基础收益-经验",required = true)
	private Integer basicExpIncome;

	@NotNull
	@ApiModelProperty(value = "基础收益-次数",required = true)
	private Integer basicTimeIncome;

	@NotBlank
	@ApiModelProperty(value = "击杀小怪收益",required = true)
	private String mobDropIncome;

	@NotBlank
	@ApiModelProperty(value = "击杀怪物收益",required = true)
	private String monsterDropIncome;

	@NotBlank
	@ApiModelProperty(value = "击杀boss收益",required = true)
	private String bossDropIncome;

}
