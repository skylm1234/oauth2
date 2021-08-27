package com.gejian.pixel.controller;

import com.gejian.pixel.strategy.context.MethodContextStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljb
 * @date 2021年08月27日 17:04
 * @description
 */
@RestController
public class TestController {
	@GetMapping("/test")
	public String test(String method){
		return MethodContextStrategy.method(method);
	}
}
