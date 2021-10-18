package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：lijianghuai
 * @date ：2021-10-18 14:51
 * @description：
 */

@ApiModel("技能简单属性")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillDTO {

	@ApiModelProperty("技能id")
	private String id;

	@ApiModelProperty("技能名称")
	private String name;
}
