package com.gejian.pixel.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysMenu extends Model<SysMenu> {

	private static final long serialVersionUID = 1L;

	/**
	 * 菜单ID
	 */
	@TableId(value = "menu_id", type = IdType.AUTO)
	private Integer menuId;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单权限标识
	 */
	private String permission;

	/**
	 * 父菜单ID
	 */
	private Integer parentId;

	/**
	 * 图标
	 */
	private String icon;

	/**
	 * 前端URL
	 */
	private String path;

	/**
	 * 排序值
	 */
	private Integer sort;

	/**
	 * 菜单类型 （0菜单 1按钮）
	 */
	private String type;

	/**
	 * 路由缓冲
	 */
	private Boolean keepAlive;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 0--正常 1--删除
	 */
	@TableLogic
	private Boolean delFlag;

}
