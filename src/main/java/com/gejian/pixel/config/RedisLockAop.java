package com.gejian.pixel.config;

import com.gejian.pixel.exception.RedisLockException;
import com.gejian.pixel.model.UserInfo;
import com.gejian.pixel.utils.UserHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
@Aspect
@Component
public class RedisLockAop {

	@Autowired
	private RedissonClient redissonClient;


	@Around("execution(public * com.gejian.pixel.service.process.*.doProcess(..))")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		UserInfo userInfo = UserHolder.get();
		if (Objects.isNull(userInfo)){
			return point.proceed();
		}
		String prefix = "pixel-user-lock";
		String processName = String.valueOf(MDC.get("processName"));
		String key = prefix + ":" + processName + ":" + userInfo.getIdentifier();
		RLock lock = redissonClient.getLock(key);
		if (lock.tryLock()) {
			try {
				return point.proceed();
			} finally {
				lock.unlock();
			}
		} else {
			log.error("Redis lock 已经被占用！key:{}", key);
			throw new RedisLockException();
		}
	}


}
