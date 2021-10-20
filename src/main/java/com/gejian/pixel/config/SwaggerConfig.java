package com.gejian.pixel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author ：lijianghuai
 * @date ：2021-09-30 11:45
 * @description：
 */

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.OAS_30)
				.apiInfo(apiInfo())
				.enable(true)
				.groupName("API V3")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.gejian.pixel.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("像素游戏后端接口文档")
				.description("像素游戏后台管理接口")
				.contact(new Contact("gejian","",""))
				.version("1.0.0")
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
