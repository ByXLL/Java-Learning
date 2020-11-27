package com.extend_demo;

public class Dog extends Animal {
    // 实现默认构造方法
    public Dog() {
    }

    // 带参构造方法 将 参数传给基类
    public Dog(String name, int age) {
        super(name, age);
    }

    // 狗 类的方法
    public void wangWang() {
        System.out.println("汪汪汪");
    }
}
