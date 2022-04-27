package com.byxll.util;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在 spring.factories 文件中加入这个类的路径
 * 就会根据当前类的配置信息，将当前类注入到IOC去
 * @author By-Lin
 */
@SuppressWarnings("all")
@Configuration
// 导入
@EnableConfigurationProperties(UtilProperties.class)
public class StringUtilConfig {

    // 声明 我们当前这个包 (jar) 需要加载到IOC容器中的Bean
    // 这样只要导入了这个包 ，使用starter，就可以完成自动装配，在项目中就可以通过 @Autowired 使用当前方法
    @Bean
    public StringUtil getStringUtil() {
        return new StringUtil();
    }
}
