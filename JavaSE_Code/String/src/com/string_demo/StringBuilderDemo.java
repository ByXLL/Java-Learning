package com.string_demo;

public class StringBuilderDemo {
    public static void main(String[] args) {
        // StringBuilder 构造方法
        // 创建一个可变字符串对象
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("stringBuilder:  " + stringBuilder);
        StringBuilder stringBuilder1 = new StringBuilder("我是你爸爸");
        System.out.println("stringBuilder.length():  " + stringBuilder1.length());

        // 链式拼接
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append("我是你爸爸").append("我是你爹");
        System.out.println(stringBuilder2);

        // 反序 hui 会修改原数据
        System.out.println(stringBuilder2.reverse());


        // StringBuilder 转 String
        String string1 = stringBuilder1.toString();
        System.out.println(string1);

        // String 转 StringBuilder   使用构造方法去 实例化一个
        String helloStr = "hello";
        StringBuilder stringBuilder3 = new StringBuilder(helloStr);

        System.out.println(stringBuilder3.append("哈哈哈"));

    }
}
