package com.gejian.pixel.service.interceptor;

import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.utils.UserHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author : zhouqiang
 * @date : 2021/8/9 10:17
 * @description:
 */
@Slf4j
@Component
public class RedisLockInterceptor {

	@Autowired
	private RedissonClient redissonClient;


	@SneakyThrows
	public boolean doFilter() {
		UserInfo userInfo = UserHolder.get();
		if (Objects.isNull(userInfo)){
			return false;
		}
		String prefix = "pixel-user-lock";
		String processName = String.valueOf(MDC.get("processName"));
		String key = prefix + ":" + processName + ":" + userInfo.getIdentifier();
		RLock lock = redissonClient.getLock(key);
		if (lock.tryLock()) {
			try {
				return false;
			} finally {
				lock.unlock();
			}
		} else {
			return true;
		}
	}


}
