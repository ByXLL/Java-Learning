package com.demo_01;

public class ArgsDemo {
    public static void main(String[] args) {

    }
    // 在参数为基本数据类型的时候
    public static void change1(int data) {
        // 在参数为基本数据类型的时候 去修改形参 不会去修改到堆内存 所以不去修改到实参
        data = 1200;
    }
    // 在参数为引用数据类型的时候
    public static void change2(int[] arry) {
        // 在参数为引用数据类型的时候 会去修改到堆内存 会影响实参
    }
}
