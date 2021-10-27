package com.gejian.pixel.dto.gamer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 16:22
 * @description：
 */

@Data
@ApiModel("玩家封禁记录Model")
public class GamerSealedDTO {

	@ApiModelProperty("封禁id")
	private Long id;

	/**
	 * 玩家id
	 */
	@ApiModelProperty("封禁玩家id")
	private Long gamerId;

	/**
	 * 封禁开始时间
	 */
	@ApiModelProperty("封禁开始时间")
	private LocalDateTime startTime;

	/**
	 * 封禁结束时间
	 */
	@ApiModelProperty("封禁结束时间")
	private LocalDateTime terminateTime;

	/**
	 * 封禁天数
	 */
	@ApiModelProperty("封禁天数")
	private Integer days;

	/**
	 * 封禁原因
	 */
	@ApiModelProperty("封禁原因")
	private String reason;
}
