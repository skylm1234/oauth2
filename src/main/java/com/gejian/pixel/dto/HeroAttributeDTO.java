package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 14:39
 * @description：
 */
@ApiModel("人数属性")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HeroAttributeDTO {

	@NotNull
	@ApiModelProperty(value = "血量",required = true)
	private Integer hp;

	@NotNull
	@ApiModelProperty(value = "攻击",required = true)
	private Integer attack;

	@NotNull
	@ApiModelProperty(value = "防御",required = true)
	private Integer defense;

	@NotNull
	@ApiModelProperty(value = "速度",required = true)
	private Integer speed;
}
