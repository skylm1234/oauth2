package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 13:37
 * @description：
 */
@ApiModel("掉落Model")
@Data
public class DropDTO {

	@ApiModelProperty(value = "掉落id",required = true)
	private String id;

	@ApiModelProperty(value = "掉落内容",required = true)
	private String content;

	@ApiModelProperty(value = "掉落描述",required = true)
	private String desc;

	@ApiModelProperty(value = "掉落描述2",required = true)
	private String desc2;
}
