package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 13:40
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class DropQueryDTO extends BasePageQuery{

	@ApiModelProperty("掉落id")
	private String id;

	@ApiModelProperty("掉落内容")
	private String content;
}
