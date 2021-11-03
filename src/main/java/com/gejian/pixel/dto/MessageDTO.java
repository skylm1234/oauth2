package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 14:14
 * @description：
 */

@Data
@ApiModel("消息model")
public class MessageDTO {

	@NotBlank
	@ApiModelProperty(value = "消息内容",required = true)
	private String message;
}
