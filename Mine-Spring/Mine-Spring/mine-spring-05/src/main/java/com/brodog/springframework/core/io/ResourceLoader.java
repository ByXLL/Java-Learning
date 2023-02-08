package com.brodog.springframework.core.io;

/**
 * 资源加载器
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public interface ResourceLoader {
    /**
     * 类路径前缀
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    /**
     * 通过路径获取资源
     * @param location      路径地址
     * @return  资源对象
     */
    Resource getResource(String location);
}
