package com.gejian.pixel.strategy.impl;

import com.gejian.pixel.strategy.MethodStrategy;

/**
 * @author ljb
 * @date 2021年08月27日 16:49
 * @description
 */
public class LoginStrategy implements MethodStrategy {
	@Override
	public String method() {
		return "login";
	}
}
