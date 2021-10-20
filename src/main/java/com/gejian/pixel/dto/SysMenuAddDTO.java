package com.gejian.pixel.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 10:57
 * @description：
 */

@Data
@ApiModel("新增菜单Model")
public class SysMenuAddDTO {

	@ApiModelProperty(value = "菜单id")
	private Integer menuId;

	/**
	 * 菜单名称
	 */
	@NotBlank(message = "菜单名称不能为空")
	@ApiModelProperty(value = "菜单名称",required = true)
	private String name;

	/**
	 * 父菜单ID
	 */
	@NotNull(message = "菜单父ID不能为空")
	@ApiModelProperty(value = "菜单父id",required = true)
	private Integer parentId;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 前端URL
	 */
	@ApiModelProperty(value = "前端路由标识路径",required = true)
	@NotNull(message = "前端路由标识路径不能为空")
	private String path;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值",required = true)
	@NotNull(message = "排序值不能为空")
	private Integer sort;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	@NotNull(message = "菜单类型不能为空")
	@ApiModelProperty(value = "菜单类型",required = true)
	private String type;

	/**
	 * 路由缓冲
	 */
	@NotNull(message = "路由缓冲不能为空")
	@ApiModelProperty(value = "路由缓冲",required = true)
	private Boolean keepAlive;

}
