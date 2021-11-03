package com.gejian.pixel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gejian.pixel.dto.user.SysUserQueryDTO;
import com.gejian.pixel.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

	/**
	 * 分页查询用户信息（含角色）
	 * @param page 分页
	 * @param sysUserQueryDTO 查询参数
	 * @return list
	 */
	IPage<SysUser> getUsersPage(Page page, @Param("query") SysUserQueryDTO sysUserQueryDTO);

	/**
	 * 通过ID查询用户信息
	 * @param id 用户ID
	 * @return userVo
	 */
	SysUser getUserById(Integer id);

	/**
	 * 通过ID查询用户信息（包括delFlag=true的）
	 * @param id
	 * @return
	 */
	SysUser getSysUser(Integer id);

}
