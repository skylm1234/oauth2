package com.gejian.pixel.service;

import cn.hutool.core.util.StrUtil;
import com.gejian.pixel.constants.AttributeKeyConstants;
import com.gejian.pixel.constants.CommandConstants;
import com.gejian.pixel.constants.RedisKeyConstants;
import com.gejian.pixel.model.UserInfo;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户拦截器
 *
 * @author ZhouQiang
 * @date 2021/8/31$
 */
@Component
@Slf4j
public class UserInterceptor {


	@Autowired
	private StringRedisTemplate stringRedisTemplate;


	/**
	 * 用户过滤器
	 *
	 * @param channel
	 * @return
	 */
	public boolean doFilter(Channel channel, String commandName) {
		if (StrUtil.equals(commandName, CommandConstants.LOGIN_REQUEST)){
			return true;
		}
		UserInfo userInfo = channel.attr(AttributeKeyConstants.USER_INFO_ATTRIBUTE_KEY).get();
		if (Objects.isNull(userInfo) || Objects.isNull(userInfo.getIdentifier())
				|| StrUtil.isBlank(userInfo.getSession())){
			return false;
		}
		String key = StrUtil.format(RedisKeyConstants.USER,userInfo.getIdentifier());
		String session = stringRedisTemplate.opsForValue().get(key);
		if (!StrUtil.equals(session,userInfo.getSession())){
			log.error("用户session已过期！id:{},session:{}"
					,userInfo.getIdentifier(),userInfo.getSession());
			return false;
		}
		return true;
	}

}
