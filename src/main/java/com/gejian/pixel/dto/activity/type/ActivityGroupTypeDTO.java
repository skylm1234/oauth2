package com.gejian.pixel.dto.activity.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 11:02
 * @description：
 */
@Data
@ApiModel("活动一级分类Model")
public class ActivityGroupTypeDTO {

	@ApiModelProperty(value = "分类Id")
	private String typeId;

	@ApiModelProperty(value = "分类名称")
	@NotBlank
	private String typeName;

}
