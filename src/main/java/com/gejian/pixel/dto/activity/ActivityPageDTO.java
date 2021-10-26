package com.gejian.pixel.dto.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 9:49
 * @description：
 */
@Data
@ApiModel("活动列表Model")
public class ActivityPageDTO {

	@ApiModelProperty("id")
	private Integer id;

	@ApiModelProperty("活动名称")
	private String title;

	@ApiModelProperty("行为key")
	private String key;

	@ApiModelProperty("活动分类")
	private Integer type;

	@ApiModelProperty("活动分类名称")
	private String typeName;

	@ApiModelProperty("活动描述")
	private String desc;

	@ApiModelProperty("比较类型")
	private Integer compareType;

	@ApiModelProperty("参数")
	private Integer parameter;

	@ApiModelProperty("上一活动id")
	private Integer precondition;

	@ApiModelProperty("下一活动id")
	private Integer next;

	@ApiModelProperty("达成条件")
	private String condition;

	@ApiModelProperty("掉落")
	private String award;

	@ApiModelProperty("进度")
	private String percent;



}
