package com.gejian.pixel.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gejian.pixel.dto.SysUserDTO;
import com.gejian.pixel.dto.SysUserQueryDTO;
import com.gejian.pixel.entity.SysUser;
import com.gejian.pixel.exception.ResourceNotFoundException;
import com.gejian.pixel.security.PrincipalUser;
import com.gejian.pixel.security.SecurityUtils;
import com.gejian.pixel.service.SysUserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@RestController
@RequestMapping("/api/users")
@Api(value = "user", tags = "用户管理")
public class UserController {

	@Autowired
	private  SysUserService userService;

	/**
	 * 获取当前用户全部信息
	 * @return 用户信息
	 */
	@GetMapping(value = { "/me" })
	@ApiOperation("获取当前登录用户信息")
	public SysUserDTO info() {
		PrincipalUser principalUser = SecurityUtils.getSysUser().orElseThrow(ResourceNotFoundException::new);
		return userService.getUserById(principalUser.getUserId()).orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 通过ID查询用户信息
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	@ApiOperation("通过ID查询用户信息")
	public SysUserDTO getInfoByUserId(@ApiParam(name = "id", value = "用户id", required = true)@PathVariable Integer id) {
		return userService.getUserById(id).orElseThrow(ResourceNotFoundException::new);
	}

	/**
	 * 删除用户信息
	 * @return R
	 */
	@DeleteMapping("/{id}")
	@ApiOperation("删除用户信息")
	public ResponseEntity<Void> userDel(@ApiParam(name = "id", value = "用户id", required = true)@PathVariable Integer id) {
		userService.removeById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 批量删除用户信息
	 * @param userIds id集合
	 * @return
	 */
	@DeleteMapping("/batch")
	@ApiOperation("批量删除用户信息")
	public ResponseEntity<Void> userBatchDel(@RequestBody List<Integer> userIds) {
		userService.removeUserByIds(userIds);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 添加用户
	 * @param sysUserDTO 用户信息
	 * @return success/false
	 */
	@PostMapping("")
	@ApiOperation("添加用户")
	public ResponseEntity<Void> user(@Valid @RequestBody SysUserDTO sysUserDTO) {
		userService.saveUser(sysUserDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * 更新用户信息
	 * @param sysUserSaveDTO 用户信息
	 * @return R
	 */
	@PutMapping("/{id}")
	@ApiOperation("更新用户信息")
	public ResponseEntity<Void> updateUser(@ApiParam(name = "id", value = "用户id", required = true)@PathVariable Integer id, @Valid @RequestBody SysUserDTO sysUserSaveDTO) {
		sysUserSaveDTO.setUserId(id);
		userService.updateUser(sysUserSaveDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 分页查询用户
	 * @param sysUserQueryDTO 查询参数列表
	 * @return 用户集合
	 */
	@GetMapping("")
	@ApiOperation(value = "分页查询用户")
	public IPage<SysUserDTO> getUserPage(SysUserQueryDTO sysUserQueryDTO) {
		return userService.getUserPage(sysUserQueryDTO);
	}

	/**
	 * 修改个人信息
	 * @param sysUserSaveDTO userDto
	 * @return success/false
	 */
	@PutMapping("/me")
	@ApiOperation("修改个人信息")
	public ResponseEntity<Void> updateUserInfo(@Valid @RequestBody SysUserDTO sysUserSaveDTO) {
		PrincipalUser principalUser = SecurityUtils.getSysUser().orElseThrow(ResourceNotFoundException::new);
		sysUserSaveDTO.setUserId(principalUser.getUserId());
		userService.updateUserInfo(sysUserSaveDTO);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return
	 */
	@GetMapping("/check")
	@ApiOperation("根据用户名查询用户信息")
	public ResponseEntity<SysUserDTO> getInfoByUsername(@ApiParam(value = "用户名",required = true) @RequestParam("username") String  username) {
		Optional<SysUser> optionalSysUser = userService.getInfoByUsername(username);
		return optionalSysUser.map(sysUser -> ResponseEntity.ok(BeanUtil.copyProperties(sysUser, SysUserDTO.class))).orElseGet(() -> ResponseEntity.noContent().build());
	}
}
