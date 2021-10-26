package com.gejian.pixel.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 14:46
 * @description：
 */

@Data
@ApiModel("活动行为")
public class ActivityActionDTO {

	@ApiModelProperty("行为key")
	private String key;

	@ApiModelProperty("行为名称")
	private String name;

	@ApiModelProperty("条件")
	private String condition;

	@ApiModelProperty("参数")
	private Integer number;
}
