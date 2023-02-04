package com.mali.travelstrategy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 静态资源映射
 * @author By-Lin
 */
@Configuration
public class PathConfig extends WebMvcConfigurerAdapter {
    //将jar文件下的对应静态资源文件路径对应到磁盘的路径(根据个人的情况修改"file:static/"的static的值
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("/static/**"). addResourceLocations("classpath:/static/","file:static/");
    }

}