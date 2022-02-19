package cn.brodog.annotation.annotations;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 使用 @interface 自定义注解时 自动继承了 Annotation 接口
 * @author By-Lin
 */
@Target(ElementType.METHOD)     // 使用范围
@Retention(RetentionPolicy.RUNTIME)     // 有效范围
@Documented     // 加入到javadoc中
@Inherited      // 子类可以继承父类的该注解
public @interface MyAnnotation {
    // 参数成员 如果只有一个参数成员，一般参数名为value，
    // 定义了参数成员，就必须携带参数可以为null / "",
    // 使用 default 设置默认值，就可以在使用的时候不传递参数
    String value() default "";
}
