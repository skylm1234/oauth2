package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 菜单查询参数
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@ApiModel(value = "菜单查询参数")
public class SysMenuQueryDTO {

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "菜单父id")
	private Integer parentId;

	/**
	 * 是否是懒加载
	 */
	@ApiModelProperty(value = "是否是懒加载")
	private boolean lazy;

}
