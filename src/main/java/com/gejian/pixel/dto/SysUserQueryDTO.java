package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-19 13:45
 * @description：
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("搜索用户Model")
public class SysUserQueryDTO extends BasePageQuery{

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("真实姓名")
	private String realName;

	@ApiModelProperty("手机号")
	private String phone;

}
