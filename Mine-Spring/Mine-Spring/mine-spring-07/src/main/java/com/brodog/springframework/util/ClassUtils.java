package com.brodog.springframework.util;

/**
 * Java 类工具类
 * @author By-BroDog
 * @createTime 2023-02-13
 */
public class ClassUtils {

    /**
     * 获取类的类加载器
     * @return
     */
    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

}
