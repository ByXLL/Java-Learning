package com.brodog;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Test01 {
    public static void main(String[] args) throws Exception {
        // 通过反射获取类的Class对象 Class.forName("包名全路径 + 类名")
        Class class1 = Class.forName("com.brodog.entity.Person");
        Class class2 = Class.forName("com.brodog.entity.Person");
        System.out.println(class1);
        /**
         * 相同一个类，在内存中只存在一个Class对象
         */
        System.out.println(class1.hashCode() == class2.hashCode());     // true
    }
}
