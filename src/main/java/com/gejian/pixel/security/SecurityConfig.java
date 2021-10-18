package com.gejian.pixel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author ：lijianghuai
 * @date ：2021-09-29 10:10
 * @description：
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private CustomizeAuthenticationFailureHandler loginFailureHandler;
	@Autowired
	private CustomizeAuthenticationSuccessHandler loginSuccessHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers( "/login").permitAll()
					.antMatchers( "/doc.html").permitAll()
					.antMatchers( "/static/**").permitAll()
					.antMatchers( "/v3/**").permitAll()
					.antMatchers( "/webjars/**").permitAll()
					.antMatchers("/swagger-resources/**").permitAll()
					.antMatchers("/consts/**").permitAll()
					.anyRequest().authenticated()
			.and()
				.formLogin().permitAll().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler)
			.and()
				.logout().logoutUrl("/logout").deleteCookies("SESSION")
				//.and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			.and().sessionManagement().invalidSessionUrl("/login").maximumSessions(5);

	}

}
