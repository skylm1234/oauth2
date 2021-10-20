package com.gejian.pixel.security;

import com.gejian.pixel.entity.SysUser;
import com.gejian.pixel.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ：lijianghuai
 * @date ：2021-10-19 14:28
 * @description：
 */

@Service
public class CustomizeUserDetailService implements UserDetailsService{
	
	@Autowired
	private SysUserService sysUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser sysUser = sysUserService.getInfoByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户未找到"));
		return new PrincipalUser(sysUser.getUserId(),sysUser.getUsername(),sysUser.getPassword(), sysUser.getDelFlag(),sysUser.getLockFlag());
	}
}
