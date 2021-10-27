package com.gejian.pixel.dto.activity.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 9:39
 * @description：
 */

@ApiModel("活动分类列表Model")
@Data
public class ActivityTypePageDTO {

	/**
	 *
	 */
	@ApiModelProperty("分类id")
	private String typeId;

	/**
	 *
	 */
	@ApiModelProperty("分类名称")
	private String typeName;

	/**
	 *
	 */
	@ApiModelProperty("排序号")
	private Integer index;

	@ApiModelProperty("层级")
	private Integer level;

	@ApiModelProperty(value = "父级分类名称")
	private String parentTypeName;

	@ApiModelProperty(value = "父级分类id")
	private String parentTypeId;

}
