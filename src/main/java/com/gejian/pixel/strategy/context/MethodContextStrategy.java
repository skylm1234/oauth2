package com.gejian.pixel.strategy.context;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gejian.pixel.strategy.MethodStrategy;
import com.gejian.pixel.strategy.factory.StrategyFactory;

public class MethodContextStrategy {

    /**
     * 获取具体的策略实现
     *
     * @param method
     * @return
     */
    public static String method(String method) {
        if (StringUtils.isBlank(method)) {
            return "method不能为空...";
        }
		MethodStrategy methodStrategy = StrategyFactory.getMethodStrategy(method);
        if (methodStrategy == null) {
            return "没有找到具体的策略...";
        }
        return methodStrategy.method();
    }
}