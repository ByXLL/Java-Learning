package cn.brodog.reflection3.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库表字段注解
 * @author By-Lin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    // 列名
    String columnName();
    // 类型
    String type();
    // 长度
    int length();
}
