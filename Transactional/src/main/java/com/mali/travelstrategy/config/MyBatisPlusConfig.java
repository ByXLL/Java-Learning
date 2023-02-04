package com.mali.travelstrategy.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatisPlusConfig 配置类
 * 设置mapper扫描目录
 * 开启事务
 * @author By-mali
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.mali.travelstrategy.mapper")
public class MyBatisPlusConfig {
    /**
     * 分页组件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
         paginationInterceptor.setOverflow(false);
        return paginationInterceptor;
    }


    /**
     * SQL 执行效率插件
     * Profile 用于声明 在什么环境下启用
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        // 设置sql执行的最大时间
        performanceInterceptor.setMaxTime(100);
        // 格式化sql
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
}
