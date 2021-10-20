package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gejian.pixel.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

	/**
	 * 通过角色编号查询菜单
	 * @param roleIds 角色ID
	 * @return
	 */
	List<SysMenu> listMenusByRoleId(@Param("roleIds") List<Integer> roleIds);

	/**
	 * 通过角色ID查询权限
	 * @param roleIds Ids
	 * @return
	 */
	List<String> listPermissionsByRoleIds(String roleIds);

}
