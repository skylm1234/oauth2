package com.gejian.pixel.dto.drop;

import com.gejian.pixel.dto.BasePageQuery;
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
public class DropQueryDTO extends BasePageQuery {

	@ApiModelProperty("掉落id")
	private String dropId;

	@ApiModelProperty("掉落内容")
	private String content;
}
