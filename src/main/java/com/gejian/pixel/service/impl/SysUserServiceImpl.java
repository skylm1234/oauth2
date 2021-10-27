package com.gejian.pixel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gejian.pixel.dto.SysUserDTO;
import com.gejian.pixel.dto.SysUserQueryDTO;
import com.gejian.pixel.entity.SysUser;
import com.gejian.pixel.mapper.SysUserMapper;
import com.gejian.pixel.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

	/**
	 * 保存用户信息
	 *
	 * @param sysUserDTO DTO 对象
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUserDTO sysUserDTO) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDTO, sysUser);
		sysUser.setDelFlag(Boolean.FALSE);
		sysUser.setPassword(ENCODER.encode("123456"));
		baseMapper.insert(sysUser);
	}

	/**
	 * 分页查询用户信息（含有角色信息）
	 *
	 * @param sysUserQueryDTO 查询参数
	 * @return
	 */
	@Override
	public IPage<SysUserDTO> getUserPage(SysUserQueryDTO sysUserQueryDTO) {
		return baseMapper.getUsersPage(sysUserQueryDTO.page(), sysUserQueryDTO).convert(record -> BeanUtil.copyProperties(record,SysUserDTO.class));
	}

	/**
	 * 通过ID查询用户信息
	 *
	 * @param id 用户ID
	 * @return 用户信息
	 */
	@Override
	public Optional<SysUserDTO> getUserById(Integer id) {
		SysUser sysUser = baseMapper.getUserById(id);
		if(sysUser == null){
			return Optional.empty();
		}
		return Optional.of(BeanUtil.copyProperties(sysUser, SysUserDTO.class));
	}

	@Override
	public Optional<SysUser> getInfoByUsername(String username) {
		SysUser condition = new SysUser();
		condition.setUsername(username);
		SysUser sysUser = this.getOne(new QueryWrapper<>(condition));
		return Optional.ofNullable(sysUser);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeUserByIds(List<Integer> userIds) {
		this.removeByIds(userIds);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateUserInfo(SysUserDTO sysUserDTO) {
		SysUser sysUser = baseMapper.getUserById(sysUserDTO.getUserId());
		SysUser user = new SysUser();
		if (StrUtil.isNotBlank(sysUserDTO.getPassword())) {
			user.setPassword(ENCODER.encode(sysUserDTO.getPassword()));
		}
		user.setPhone(sysUserDTO.getPhone());
		user.setUserId(sysUser.getUserId());
		this.updateById(user);
	}

	@Override
	public Boolean updateUser(SysUserDTO sysUserDTO) {
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserDTO, sysUser);
		sysUser.setUpdateTime(LocalDateTime.now());

		if (StrUtil.isNotBlank(sysUserDTO.getPassword())) {
			sysUser.setPassword(ENCODER.encode(sysUserDTO.getPassword()));
		}
		this.updateById(sysUser);
		return Boolean.TRUE;
	}

	/**
	 * 生成用户名
	 *
	 * @return
	 */
	private String genUserName() {
		return DateUtil.format(new Date(), "yyyyMMddHHss") +
				RandomUtil.randomString(6);
	}

}
