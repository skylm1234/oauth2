package com.gejian.pixel.security;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gejian.pixel.dto.SysUserDTO;
import com.gejian.pixel.service.SysUserService;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestValidator;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * @author ：lijianghuai
 * @date ：2021-09-30 11:00
 * @description：
 */

@Component
@Slf4j
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final String BASIC_ = "Basic ";
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Lazy
	@Autowired
	private AuthorizationServerTokenServices defaultAuthorizationServerTokenServices;

	@Autowired
	private SysUserService sysUserService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BASIC_)) {
			throw new UnapprovedClientAuthenticationException("请求头中client信息为空");
		}

		try {
			String[] tokens = AuthUtils.extractAndDecodeHeader(header);
			assert tokens.length == 2;
			String clientId = tokens[0];
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
			// 校验secret
			if (!clientDetails.getClientSecret().equalsIgnoreCase(tokens[1])) {
				throw new InvalidClientException("Given client ID does not match authenticated client");
			}
			TokenRequest tokenRequest = new TokenRequest(MapUtil.newHashMap(), clientId, clientDetails.getScope(),
					"web");
			// 校验scope
			new DefaultOAuth2RequestValidator().validateScope(tokenRequest, clientDetails);
			OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
			OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
			OAuth2AccessToken oAuth2AccessToken = defaultAuthorizationServerTokenServices
					.createAccessToken(oAuth2Authentication);
			response.setCharacterEncoding(CharsetUtil.UTF_8);
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			PrintWriter printWriter = response.getWriter();
			Optional<PrincipalUser> optionalPrincipalUser = SecurityUtils.getSysUser(authentication);
			optionalPrincipalUser.ifPresent(principalUser -> {
				SysUserDTO sysUserDTO = BeanUtil.copyProperties(principalUser, SysUserDTO.class);
				((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(ImmutableMap.of("user_info", sysUserDTO));
			});
			printWriter.append(objectMapper.writeValueAsString(oAuth2AccessToken));
		}
		catch (IOException e) {
			throw new BadCredentialsException("Failed to decode basic authentication token");
		}
	}
}
