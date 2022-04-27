package com.brodog.principle;

import com.byxll.util.EnableStringUtil;
import com.byxll.util.StringUtilConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
@SpringBootApplication

// 方案1：直接使用 Import 将 @Configuration 修饰的类进行导入
//@Import(StringUtilConfig.class)
// 方案2：使用自定义注解 xxxEnableConfig
//@EnableStringUtil
// 方案3：使用spring.factories的方式
public class Start {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Start.class, args);
    }
}
