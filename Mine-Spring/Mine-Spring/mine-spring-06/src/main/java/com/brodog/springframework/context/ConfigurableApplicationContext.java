package com.brodog.springframework.context;

import com.brodog.springframework.beans.BeansException;

/**
 * 可配置的 应用上下文接口
 * @author By-BroDog
 * @createTime 2023-02-14
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新上下文
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
