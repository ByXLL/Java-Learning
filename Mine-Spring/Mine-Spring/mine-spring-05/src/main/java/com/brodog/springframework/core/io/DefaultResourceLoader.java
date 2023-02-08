package com.brodog.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author By-BroDog
 * @createTime 2023-02-08
 */
public class DefaultResourceLoader implements ResourceLoader{

    /**
     * 通过路径获取资源
     *
     * @param location 路径地址
     * @return 资源对象
     */
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 如果是 classpath为路径前缀 则代表是类路径下的 通过创建类路径资源对象返回
            System.out.println("当前加载的是类路径下的资源");
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        else {
            try {
                URL url = new URL(location);
                System.out.println("当前加载的是远程地址的资源");
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                System.out.println("当前加载的是文件系统中的资源");
                return new FileSystemResource(location);
            }
        }
    }
}
