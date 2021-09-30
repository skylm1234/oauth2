package com.gejian.pixel.security;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ：lijianghuai
 * @date ：2021-09-29 10:25
 * @description：
 */

@Component
@Slf4j
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
		String error;
		if(e instanceof BadCredentialsException) {
			error = "密码错误";
		} else if(e instanceof DisabledException) {
			error = "账户被禁用";
		} else if(e instanceof AccountExpiredException) {
			error = "账户已过期";
		} else if(e instanceof LockedException) {
			error = "账户被锁定";
		} else if(e instanceof CredentialsExpiredException) {
			error = "账户凭证过期";
		} else if(e instanceof UsernameNotFoundException) {
			error = "账户不存在";
		} else {
			error = "未知错误";
		}
		log.error(e.getMessage(),e);
		httpServletResponse.setStatus(HttpStatus.HTTP_BAD_REQUEST);
		httpServletResponse.getWriter().write(error);
	}
}
