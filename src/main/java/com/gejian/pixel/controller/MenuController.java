package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import com.gejian.pixel.dto.SysMenuAddDTO;
import com.gejian.pixel.dto.SysMenuDTO;
import com.gejian.pixel.dto.SysMenuQueryDTO;
import com.gejian.pixel.entity.SysMenu;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author lengleng
 * @date 2017/10/31
 */
@RestController
@RequestMapping("/api/menus")
@Api(value = "menu", tags = "菜单管理")
public class MenuController {

	@Autowired
	private SysMenuService sysMenuService;

	/**
	 * 返回树形菜单集合
	 * @param menuQueryDTO 查询参数
	 * @return 树形菜单
	 */
	@GetMapping("")
	@ApiOperation("菜单列表")
	public List<Tree<Integer>> list(@RequestBody(required = false) SysMenuQueryDTO menuQueryDTO) {
		if(menuQueryDTO == null){
			menuQueryDTO = new SysMenuQueryDTO();
		}
		return sysMenuService.treeMenu(menuQueryDTO.isLazy(), menuQueryDTO.getParentId());
	}

	@GetMapping("/navigation")
	@ApiOperation("菜单列表")
	public List<Tree<Integer>> listLeft() {
		return sysMenuService.getUserMenu(new SysMenuQueryDTO());
	}


	/**
	 * 通过ID查询菜单的详细信息
	 * @return 菜单详细信息
	 */
	@GetMapping("/{id}")
	@ApiOperation("菜单明细")
	public SysMenuDTO getById(@ApiParam(name = "id", value = "菜单id", required = true) @PathVariable Integer id) {
		SysMenu sysMenu = sysMenuService.selectById(id).orElseThrow(ResourceNotFoundException::new);
		return BeanUtil.copyProperties(sysMenu, SysMenuDTO.class);
	}

	/**
	 * 新增菜单
	 * @param menuAddDTO 菜单信息
	 * @return 含ID 菜单信息
	 */
	@PostMapping("")
	@ApiOperation("新增菜单")
	public ResponseEntity<Void> save(@Valid @RequestBody SysMenuAddDTO menuAddDTO) {
		sysMenuService.save( BeanUtil.copyProperties(menuAddDTO,SysMenu.class));
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 删除菜单
	 * @param id 菜单id
	 * @return
	 */
	@DeleteMapping("/{id}")
	@ApiOperation("删除菜单")
	public ResponseEntity<Void> removeById(@ApiParam(name = "id", value = "菜单id", required = true) @PathVariable Integer id) {
		sysMenuService.removeMenuById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 更新菜单
	 * @param menuAddDTO
	 * @return
	 */
	@PutMapping("/{id}")
	@ApiOperation("更新菜单")
	public ResponseEntity<Void> update(@ApiParam(name = "id", value = "菜单id", required = true) @PathVariable Integer id, @Valid @RequestBody SysMenuAddDTO menuAddDTO) {
		menuAddDTO.setMenuId(id);
		sysMenuService.updateMenuById(menuAddDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
