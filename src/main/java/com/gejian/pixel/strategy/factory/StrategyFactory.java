package com.gejian.pixel.strategy.factory;

import com.gejian.pixel.strategy.MethodStrategy;
import com.gejian.pixel.strategy.enmu.MethodEnumStrategy;

public class StrategyFactory {

    /**
     * 使用策略工厂获取具体策略实现
     * @param method
     * @return
     */
    public static MethodStrategy getMethodStrategy(String method) {
        try {
            return (MethodStrategy) Class.forName(MethodEnumStrategy.getClassNameByCode(method)).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}