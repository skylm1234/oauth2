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

	@ApiModelProperty("掉落id")
	private String id;

	@ApiModelProperty("掉落内容")
	private String content;

	@ApiModelProperty("掉落描述")
	private String desc;

	@ApiModelProperty("掉落描述2")
	private String desc2;
}
