package com.gejian.pixel.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 15:45
 * @description：
 */

@ApiModel("上一关/下一关Model")
@Data
public class ActivityRefrenceDTO {

	@ApiModelProperty("活动id")
	private Integer id;

	@ApiModelProperty("活动名称")
	private String title;
}
