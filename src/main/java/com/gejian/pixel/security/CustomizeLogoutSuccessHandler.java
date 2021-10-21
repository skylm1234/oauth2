package com.gejian.pixel.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：lijianghuai
 * @date ：2021-09-30 10:57
 * @description：
 */

@Component
@Slf4j
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
	@Autowired
	private TokenStore tokenStore ;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		try{
			String accessToken = request.getParameter("access_token");

			if(accessToken == null){
				String authorization = request.getHeader("Authorization");
				String[] strings = StringUtils.split(authorization, " ");
				accessToken = strings[1];
			}
			if(accessToken != null){
				OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
				log.info("token =" +oAuth2AccessToken.getValue());
				tokenStore.removeAccessToken(oAuth2AccessToken);
			}
		}catch (Exception e){
			log.warn("logout may be error",e);
		}

	}
}
