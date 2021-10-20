package com.gejian.pixel.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gejian.pixel.dto.SysUserDTO;
import com.gejian.pixel.dto.SysUserQueryDTO;
import com.gejian.pixel.entity.SysUser;

import java.util.List;
import java.util.Optional;

/**
 * @author lengleng
 * @date 2019/2/1
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 分页查询用户信息（含有角色信息）
	 * @param sysUserQueryDTO 查询参数
	 * @return
	 */
	IPage<SysUserDTO> getUserPage(SysUserQueryDTO sysUserQueryDTO);

	/**
	 * 批量删除用户信息
	 * @param userIds 用户uid集合
	 */
	void removeUserByIds(List<Integer> userIds);

	/**
	 * 更新当前用户基本信息
	 * @param sysUserDTO 用户信息
	 */
	void updateUserInfo(SysUserDTO sysUserDTO);

	/**
	 * 更新指定用户信息
	 * @param sysUserDTO 用户信息
	 * @return
	 */
	Boolean updateUser(SysUserDTO sysUserDTO);

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return 用户信息
	 */
	Optional<SysUserDTO> getUserById(Integer id);

	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	Optional<SysUser> getInfoByUsername(String username);

	/**
	 * 保存用户信息
	 * @param sysUserDTO DTO 对象
	 */
	void saveUser(SysUserDTO sysUserDTO);

}
