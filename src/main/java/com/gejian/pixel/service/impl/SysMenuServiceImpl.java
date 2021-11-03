package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.constants.CommonConstants;
import com.gejian.pixel.dto.menu.SysMenuAddDTO;
import com.gejian.pixel.dto.menu.SysMenuQueryDTO;
import com.gejian.pixel.entity.SysMenu;
import com.gejian.pixel.enums.MenuTypeEnum;
import com.gejian.pixel.mapper.SysMenuMapper;
import com.gejian.pixel.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

	/**
	 * 级联删除菜单
	 * @param id 菜单ID
	 * @return true成功, false失败
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean removeMenuById(Integer id) {
		// 查询父节点为当前节点的节点
		List<SysMenu> menuList = this.list(Wrappers.<SysMenu>query().lambda().eq(SysMenu::getParentId, id));

		Assert.isTrue(CollUtil.isEmpty(menuList), "菜单含有下级不能删除");
		// 删除当前菜单及其子菜单
		return this.removeById(id);
	}

	@Override
	public Boolean updateMenuById(SysMenuAddDTO menuAddDTO) {
		return this.updateById(BeanUtil.copyProperties(menuAddDTO,SysMenu.class));
	}

	@Override
	public Optional<SysMenu> selectById(Integer id) {
		return Optional.ofNullable(this.getById(id));
	}

	/**
	 * 构建树查询 1. 不是懒加载情况，查询全部 2. 是懒加载，根据parentId 查询 2.1 父节点为空，则查询ID -1
	 * @param lazy 是否是懒加载
	 * @param parentId 父节点ID
	 * @return
	 */
	@Override
	public List<Tree<Integer>> treeMenu(boolean lazy, Integer parentId) {
		if (!lazy) {
			List<TreeNode<Integer>> collect = baseMapper
					.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)).stream()
					.map(getNodeFunction()).collect(Collectors.toList());
			return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
		}

		Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;

		List<TreeNode<Integer>> collect = baseMapper
				.selectList(
						Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getParentId, parent).orderByAsc(SysMenu::getSort))
				.stream().map(getNodeFunction()).collect(Collectors.toList());

		return TreeUtil.build(collect, parent);
	}

	/**
	 * 查询菜单
	 * @param all 全部菜单
	 * @param parentId 父节点ID
	 * @return
	 */
	@Override
	public List<Tree<Integer>> filterMenu(Set<SysMenu> all, Integer parentId) {
		List<TreeNode<Integer>> collect = all.stream()
				.filter(menu -> MenuTypeEnum.LEFT_MENU.getType().equals(menu.getType())).map(getNodeFunction())
				.collect(Collectors.toList());
		Integer parent = parentId == null ? CommonConstants.MENU_TREE_ROOT_ID : parentId;
		return TreeUtil.build(collect, parent);
	}

	@Override
	public List<Tree<Integer>> getUserMenu( SysMenuQueryDTO menuQueryDTO) {
		// 获取符合条件的菜单
		Set<SysMenu> all = new HashSet<>(baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery().orderByAsc(SysMenu::getSort)));
		return filterMenu(all, menuQueryDTO.getParentId());
	}


	@NotNull
	private Function<SysMenu, TreeNode<Integer>> getNodeFunction() {
		return menu -> {
			TreeNode<Integer> node = new TreeNode<>();
			node.setId(menu.getMenuId());
			node.setName(menu.getName());
			node.setParentId(menu.getParentId());
			node.setWeight(menu.getSort());
			// 扩展属性
			Map<String, Object> extra = new HashMap<>();
			extra.put("icon", menu.getIcon());
			extra.put("path", menu.getPath());
			extra.put("type", menu.getType());
			extra.put("permission", menu.getPermission());
			extra.put("label", menu.getName());
			extra.put("sort", menu.getSort());
			extra.put("keepAlive", menu.getKeepAlive());
			node.setExtra(extra);
			return node;
		};
	}

}
