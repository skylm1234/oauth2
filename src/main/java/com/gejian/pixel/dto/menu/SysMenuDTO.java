package com.gejian.pixel.dto.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 11:03
 * @description：
 */

@Data
@ApiModel("菜单Model")
public class SysMenuDTO {
	/**
	 * 菜单ID
	 */
	@ApiModelProperty(value = "菜单id")
	private Integer menuId;

	/**
	 * 菜单名称
	 */
	@ApiModelProperty(value = "菜单名称",required = true)
	private String name;

	/**
	 * 菜单权限标识
	 */
	@ApiModelProperty(value = "菜单权限标识")
	private String permission;

	/**
	 * 父菜单ID
	 */
	@ApiModelProperty(value = "菜单父id")
	private Integer parentId;

	/**
	 * 图标
	 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/**
	 * 前端URL
	 */
	@ApiModelProperty(value = "前端路由标识路径")
	private String path;

	/**
	 * 排序值
	 */
	@ApiModelProperty(value = "排序值")
	private Integer sort;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	private String type;

	/**
	 * 路由缓冲
	 */
	@ApiModelProperty(value = "路由缓冲")
	private Boolean keepAlive;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 0--正常 1--删除
	 */
	@ApiModelProperty(value = "删除标记")
	private Boolean delFlag;
}
