package com.brodog.aop.entity;

/**
 * @author By-Lin
 */
public class Children {
    public static String a = "子----静态变量";
    public String b = "子----普通变量";
    static {
        System.out.println("子----静态初始化块");
    }
    {
        System.out.println("父----初始化块");
    }

    public Children() {
        System.out.println("子类----构造器");
    }

    public static void main(String[] args) {
        System.out.println("子类----main方法");
    }
}
