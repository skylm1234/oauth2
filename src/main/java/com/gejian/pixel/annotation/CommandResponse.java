package com.gejian.pixel.annotation;

import java.lang.annotation.*;

/**
 * @author ZhouQiang
 * @date 2021/8/31$
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommandResponse {

	String value();

}
