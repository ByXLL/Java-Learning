package com.brodog.principle;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 2、自定义注解
 * 用于启用某个类的自动装配
 * 将Bean装配到IOC中去，实现Starter
 * @author By-Lin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MyDefineImportSelector.class)
public @interface EnableDefineService {

}
