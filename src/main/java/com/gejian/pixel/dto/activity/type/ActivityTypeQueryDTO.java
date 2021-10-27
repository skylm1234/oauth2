package com.gejian.pixel.dto.activity.type;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-27 9:37
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@ApiModel("活动分类查询Model")
@Data
public class ActivityTypeQueryDTO extends BasePageQuery {

	@ApiModelProperty("分类名称")
	private String typeName;
}
