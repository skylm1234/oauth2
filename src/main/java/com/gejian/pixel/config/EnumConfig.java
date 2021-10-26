package com.gejian.pixel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：lijianghuai
 * @date ：2021-10-26 16:28
 * @description：
 */
@Configuration
public class EnumConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverterFactory(new CodeEnumConverter());
	}
}
