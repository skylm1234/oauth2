package com.gejian.pixel.dto.gamer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ：lijianghuai
 * @date ：2021-10-28 14:04
 * @description：
 */
@Data
@ApiModel("批量删除")
public class GamerDeleteDTO {

	@ApiModelProperty(value = "id集合",required = true)
	@NotNull
	private List<Integer> ids;
}
