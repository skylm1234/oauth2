package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-10-15 17:48
 * @description：
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("英雄列表查询参数")
public class HeroListRequestDTO extends BasePageQuery{

	@ApiModelProperty("人物")
	private String name;

	@ApiModelProperty(value = "职业",allowableValues = "1：战士；2：刺客；4：辅助")
	private Integer role;

	@ApiModelProperty(value = "初始颜色",allowableValues = "1：绿；2：蓝，3：紫；4：橙")
	private Integer color;
}
