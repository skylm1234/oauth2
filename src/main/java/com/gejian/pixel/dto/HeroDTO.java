package com.gejian.pixel.dto;

import com.gejian.pixel.enums.HeroLevelColorEnum;
import com.gejian.pixel.enums.HeroRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 16:38
 * @description：
 */
@ApiModel("英雄DTO")
@Data
public class HeroDTO {

	@ApiModelProperty("id")
	private Integer id;

	@NotBlank
	@ApiModelProperty("人物")
	private String name;

	@NotNull
	@ApiModelProperty(value = "职业",allowableValues = "1：战士；2：刺客；4：辅助")
	private HeroRoleEnum role;

	@NotNull
	@ApiModelProperty(value = "初始颜色",allowableValues = "1：绿；2：蓝，3：紫；4：橙")
	private HeroLevelColorEnum color;

	@NotNull
	@ApiModelProperty(value = "基础数值",required = true)
	private HeroAttributeDTO basicData;

	@NotNull
	@ApiModelProperty(value = "1星-5星数值",required = true)
	private HeroAttributeDTO basic1_5Data;

	@NotNull
	@ApiModelProperty(value = "6星-10星数值",required = true)
	private HeroAttributeDTO basic6_10Data;

	@NotNull
	@ApiModelProperty(value = "11星-15星数值",required = true)
	private HeroAttributeDTO basic11_15Data;

	@NotNull
	@ApiModelProperty(value = "16星-20星数值",required = true)
	private HeroAttributeDTO basic16_20Data;

	@NotNull
	@ApiModelProperty(value = "21星-25星数值",required = true)
	private HeroAttributeDTO basic21_25Data;

	@NotNull
	@ApiModelProperty(value = "基础数值成长",required = true)
	private HeroAttributeDTO upgradeData;

	@NotNull
	@ApiModelProperty(value = "1星-5星数值成长",required = true)
	private HeroAttributeDTO upgrade1_5Data;

	@NotNull
	@ApiModelProperty(value = "6星-10星数值成长",required = true)
	private HeroAttributeDTO upgrade6_10Data;

	@NotNull
	@ApiModelProperty(value = "11星-15星数值成长",required = true)
	private HeroAttributeDTO upgrade11_15Data;

	@NotNull
	@ApiModelProperty(value = "16星-20星数值成长",required = true)
	private HeroAttributeDTO upgrade16_20Data;

	@NotNull
	@ApiModelProperty(value = "21星-25星数值成长",required = true)
	private HeroAttributeDTO upgrade21_25Data;

	@NotNull
	@ApiModelProperty(value = "手动技能",required = true)
	private SkillDTO skill1;

	@NotNull
	@ApiModelProperty(value = "自动技能",required = true)
	private SkillDTO skill2;

	@NotNull
	@ApiModelProperty(value = "被动技能1",required = true)
	private SkillDTO skill3;

	@NotNull
	@ApiModelProperty(value = "被动技能2",required = true)
	private SkillDTO skill4;

	@NotNull
	@ApiModelProperty(value = "复活单价",required = true)
	private Integer alivePrice;
}
