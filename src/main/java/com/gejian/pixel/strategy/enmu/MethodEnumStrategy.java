package com.gejian.pixel.strategy.enmu;

import org.springframework.util.StringUtils;


public enum MethodEnumStrategy {

    LOGIN("login","com.gejian.pixel.strategy.impl.LoginStrategy"),

    REGISTER("register","com.gejian.pixel.strategy.impl.RegisterStrategy");


    private String method;
    private String className;

    MethodEnumStrategy() {
    }

    MethodEnumStrategy(String method, String className) {
        this.method = method;
        this.className = className;
    }

    public static String getClassNameByCode(String method) {
        String className = "";
        if (StringUtils.isEmpty(method)) {
            return className;
        }

        for (MethodEnumStrategy e : MethodEnumStrategy.values()) {
            if (e.method.equalsIgnoreCase(method)) {
                className = e.className;
                break;
            }
        }
        return className;
    }

    public String getMethod() {
        return method;
    }

    public String getClassName() {
        return className;
    }
    
}