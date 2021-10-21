package com.gejian.pixel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @author ：lijianghuai
 * @date ：2021-09-29 10:10
 * @description：
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private CustomizeAuthenticationFailureHandler loginFailureHandler;
	@Autowired
	private CustomizeAuthenticationSuccessHandler loginSuccessHandler;

	@Autowired
	private CustomizeUserDetailService customizeUserDetailService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.headers().frameOptions().disable()
			.and()
				.authorizeRequests()
					.antMatchers("/oauth/**").permitAll()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.antMatchers( "/doc.html").permitAll()
					.antMatchers( "/static/**").permitAll()
					.antMatchers( "/v3/**").permitAll()
					.antMatchers( "/webjars/**").permitAll()
					.antMatchers("/swagger-resources/**").permitAll()
					.antMatchers("/consts/**").permitAll()
					.anyRequest().authenticated()
				.and().userDetailsService(customizeUserDetailService);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		 return super.authenticationManagerBean();
	}

	@Bean
	@Qualifier("redisTokenStore")
	public TokenStore redisTokenStore(RedisConnectionFactory redisConnectionFactory) {
		return new RedisTokenStore(redisConnectionFactory);
	}
}
