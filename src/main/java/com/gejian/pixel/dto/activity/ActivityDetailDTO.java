package com.gejian.pixel.dto.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 16:01
 * @description：
 */

@Data
public class ActivityDetailDTO {

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty(value = "活动名称",required = true)
	private String title;

	@ApiModelProperty(value = "行为key",required = true)
	private String key;

	@ApiModelProperty(value = "活动分类id",required = true)
	private Integer type;

	@ApiModelProperty("活动分类名称")
	private String typeName;

	@ApiModelProperty("活动分类所属组id")
	private Integer group;

	@ApiModelProperty("活动分类所属组名称")
	private String groupName;

	@ApiModelProperty(value = "参数",required = true)
	private Integer parameter;

	@ApiModelProperty("上一活动id")
	private Integer precondition;

	@ApiModelProperty("下一活动id")
	private Integer next;

	@ApiModelProperty(value = "达成条件",required = true)
	private String condition;

	@ApiModelProperty(value = "掉落",required = true)
	private String award;
}
