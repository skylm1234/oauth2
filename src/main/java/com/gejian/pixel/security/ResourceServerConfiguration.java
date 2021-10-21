package com.gejian.pixel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author ：lijianghuai
 * @date ：2021-10-21 11:27
 * @description：
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	private static final String RESOURCE_ID = "*";

	@Autowired
	private ResourceServerTokenServices tokenServices;

	@Autowired
	private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(true).tokenServices(tokenServices);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/**").authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
				.and()
				.logout()
				.logoutUrl("/oauth/logout")
				.logoutSuccessHandler(customizeLogoutSuccessHandler);
	}
}
