package com.gejian.pixel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author ：lijianghuai
 * @date ：2021-10-20 16:36
 * @description：
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	private AuthenticationManager authenticationManagerBean;

	@Autowired
	private CustomizeAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private CustomizeUserDetailService userDetailsService;

	@Autowired
	@Qualifier("redisTokenStore")
	private TokenStore redisTokenStore;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * password模式需要提供一个AuthenticationManager到AuthorizationServerEndpointsConfigurer
	 * @param authorizationServerEndpointsConfigurer
	 * @throws Exception
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {
		authorizationServerEndpointsConfigurer.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
				.userDetailsService(userDetailsService)
				.authenticationManager(authenticationManagerBean)
				.tokenStore(redisTokenStore)
				.reuseRefreshTokens(true);
	}
	@Override
	public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
		clientDetailsServiceConfigurer.inMemory()
				.withClient("pig")
				.secret(passwordEncoder.encode("pig"))
				.scopes("server")
				.authorizedGrantTypes("password","refresh_token","authorization_code")
				.accessTokenValiditySeconds(60 * 10)
				.refreshTokenValiditySeconds(60 * 60 * 8);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.allowFormAuthenticationForClients()
				.authenticationEntryPoint(authenticationEntryPoint)
				.checkTokenAccess("permitAll()")
				.tokenKeyAccess("permitAll()");
	}

	@Bean
	public ResourceServerTokenServices tokenService() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(redisTokenStore);
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

}
