package com.string_demo;

public class StringDemo {
    public static void main(String[] args) {
        // 使用构造函数去创建字符串对象  开辟了不同的堆内存
        char[] chs = {'a','b','c'};
        String s1 = new String(chs);
        String s2 = new String(chs);

        // 通过直接赋值获取到了两个字符串对象
        // 通过""方式给出的字符串，只要顺序、大小写相同 无论在代码中出现几次
        // jvm 都只会建立一个String对象，并在字符串池中维护, 后面再创建则直接参考字符串池中的String对象
        String s3 = "abc";
        String s4 = "abc";

        // 比较字符串对象地址是否相等
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s3 == s4);
        System.out.println("------------");

        // 比较字符串内容是否相同 需要通过 equals() 进行获取
        // 因为字符串是对象， 直接去 == 判断的是他们的地址
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s3.equals(s4));
    }
}
