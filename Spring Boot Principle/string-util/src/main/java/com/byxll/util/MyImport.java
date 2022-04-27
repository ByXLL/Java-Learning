package com.byxll.util;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 通过 实现 ImportSelector 接口 重写selectImports()
 * 在方法内部声明，我们需要注入的bean
 * @author By-Lin
 */
public class MyImport implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{StringUtilConfig.class.getName()};
    }
}
