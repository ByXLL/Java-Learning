package com.brodog.springframework.beans.factory.config;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public interface ConfigurableBeanFactory {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
