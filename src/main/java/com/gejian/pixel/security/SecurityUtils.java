package com.gejian.pixel.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author ：lijianghuai
 * @date ：2021-10-19 14:51
 * @description：
 */
@UtilityClass
public class SecurityUtils {
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public Optional<PrincipalUser> getSysUser() {
		Object principal = getAuthentication().getPrincipal();
		if (principal instanceof PrincipalUser) {
			return Optional.of((PrincipalUser) principal);
		}
		return Optional.empty();
	}
}
