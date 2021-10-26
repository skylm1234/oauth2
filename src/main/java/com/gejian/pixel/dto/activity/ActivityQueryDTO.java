package com.gejian.pixel.dto.activity;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 9:52
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("活动搜索Model")
public class ActivityQueryDTO extends BasePageQuery {

	@ApiModelProperty("活动名称")
	private String title;

	@ApiModelProperty("活动分类")
	private Integer type;
}
