package com.mali.travelstrategy.annotation;

import java.lang.annotation.*;

/**
 * 自定义日志注解
 * @author By-Lin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface MyLog {
    String title();
    String data();
}
