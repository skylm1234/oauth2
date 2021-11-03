package com.gejian.pixel.dto.sysupgrade;

import com.gejian.pixel.dto.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：lijianghuai
 * @date ：2021-11-03 14:37
 * @description：
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("更新管理查询参数")
public class SysUpgradeQueryDTO extends BasePageQuery {

	@ApiModelProperty("更新标题")
	private String title;
}
