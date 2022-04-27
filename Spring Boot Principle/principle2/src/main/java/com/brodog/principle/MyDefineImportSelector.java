package com.brodog.principle;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 3、自定义 选择性导入类 同时通过 @Import 在自定义开启Bean的注解上进行导入
 * 通过实现 ImportSelector 接口 重写 selectImports 选择性导入我们自己的类
 * @author By-Lin
 */
public class MyDefineImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{UserService.class.getName()};
    }
}
