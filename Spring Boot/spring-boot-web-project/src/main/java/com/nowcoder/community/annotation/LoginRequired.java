package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 注解   用于登录校验
 */
@Target(ElementType.METHOD)  // 元注解 声明目标
@Retention(RetentionPolicy.RUNTIME)    // 元注解 声明有效时间  运行时
public @interface LoginRequired {

}
