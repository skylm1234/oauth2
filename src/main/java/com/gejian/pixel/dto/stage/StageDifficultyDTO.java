package com.gejian.pixel.dto.stage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：lijianghuai
 * @date ：2021-10-28 16:41
 * @description：
 */

@Data
@ApiModel("关卡难度DTO")
@AllArgsConstructor
@NoArgsConstructor
public class StageDifficultyDTO {

	@ApiModelProperty("难度类型")
	private Integer type;

	@ApiModelProperty("难度名称")
	private String name;
}
