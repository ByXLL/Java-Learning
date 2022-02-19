package cn.brodog.reflection3.annotation;

import java.lang.annotation.*;

/**
 * Table 注解，声明对应数据库表
 * @author By-Lin
 */
@Target(ElementType.TYPE)
@Inherited()
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
