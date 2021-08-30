package com.gejian.pixel.controller;

import com.gejian.pixel.strategy.context.MethodContextStrategy;
import com.gejian.pixel.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljb
 * @date 2021年08月27日 17:04
 * @description
 */
@RestController
@RequiredArgsConstructor
public class TestController {

	private final RedisTemplate redisTemplate;

	@GetMapping("/test")
	public String test(String method){
		String identifier = Helper.generateUserIdentifier(redisTemplate);
		Helper.setItemValue(redisTemplate, identifier, "testKey", 100, null);
		Integer itemCount = Helper.itemCount(redisTemplate, identifier, "testKey");
		System.out.println("stringValue = " + itemCount);

		return MethodContextStrategy.method(method);
	}
}
