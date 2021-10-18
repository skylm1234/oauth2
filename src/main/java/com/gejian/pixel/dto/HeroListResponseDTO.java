package com.gejian.pixel.dto;

import com.gejian.pixel.enums.HeroLevelColorEnum;
import com.gejian.pixel.enums.HeroRoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 16:39
 * @description：
 */

@ApiModel("人物列表返回结果")
@Data
public class HeroListResponseDTO {

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty("人物")
	private String name;

	@ApiModelProperty(value = "职业",allowableValues = "1：战士；2：刺客；4：辅助")
	private HeroRoleEnum role;

	@ApiModelProperty(value = "初始颜色",allowableValues = "1：绿；2：蓝，3：紫；4：橙")
	private HeroLevelColorEnum color;

	@ApiModelProperty("数值")
	private String basicData;

	@ApiModelProperty("数值成长")
	private String upgradeData;

	@ApiModelProperty("手动技能")
	private String skill1;

	@ApiModelProperty("自动技能")
	private String skill2;

	@ApiModelProperty("被动技能1")
	private String skill3;

	@ApiModelProperty("被动技能2")
	private String skill4;

	@ApiModelProperty("复活单价")
	private Integer alivePrice;

}
