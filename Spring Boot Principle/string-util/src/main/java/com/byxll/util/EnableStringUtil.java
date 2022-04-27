package com.byxll.util;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过注解，完成 start Bean的注入
 * 核心是  Import 注解
 * 这个时候在启动类上使用这个注解 就可以完成对需要Bean的注入
 * @author By-Lin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MyImport.class)
public @interface EnableStringUtil {
}
