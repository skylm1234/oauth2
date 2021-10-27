package com.gejian.pixel.dto.gamer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 16:22
 * @description：
 */

@Data
@ApiModel("玩家封禁记录Model")
public class GamerSealedPatchDTO {

	/**
	 * 封禁天数
	 */
	@ApiModelProperty(value = "封禁天数",required = true)
	private Integer days;

	/**
	 * 封禁原因
	 */
	@ApiModelProperty(value = "封禁原因",required = true)
	private String reason;

	@ApiModelProperty(value = "玩家id",hidden = true)
	private Long id;
}
