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
@ApiModel("活动分类Model")
public class ActivityTypeDTO {

	@ApiModelProperty(value = "分类Id")
	private String typeId;

	@ApiModelProperty(value = "分类名称",required = true)
	@NotBlank
	private String typeName;

	@ApiModelProperty(value = "排序号")
	private Integer index;

	@ApiModelProperty(value = "父级分类名称")
	private String parentTypeName;

	@ApiModelProperty(value = "父级分类id")
	private String parentTypeId;
}
