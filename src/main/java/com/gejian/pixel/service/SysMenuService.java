package com.gejian.pixel.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.menu.SysMenuAddDTO;
import com.gejian.pixel.dto.menu.SysMenuQueryDTO;
import com.gejian.pixel.entity.SysMenu;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface SysMenuService extends IService<SysMenu> {


	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return true成功, false失败
	 */
	Boolean removeMenuById(Integer id);

	/**
	 * 更新菜单信息
	 * @param sysMenu 菜单信息
	 * @return 成功、失败
	 */
	Boolean updateMenuById(SysMenuAddDTO sysMenu);

	Optional<SysMenu> selectById(Integer id);

	/**
	 * 构建树
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return
	 */
	List<Tree<Integer>> treeMenu(boolean lazy, Integer parentId);

	/**
	 * 查询菜单
	 * @param menuSet
	 * @param parentId
	 * @return
	 */
	List<Tree<Integer>> filterMenu(Set<SysMenu> menuSet, Integer parentId);

	/**
	 * 返回当前用户的树形菜单集合
	 * @param menuQueryDTO 查询参数
	 * @return
	 */
	List<Tree<Integer>> getUserMenu(SysMenuQueryDTO menuQueryDTO);

}
