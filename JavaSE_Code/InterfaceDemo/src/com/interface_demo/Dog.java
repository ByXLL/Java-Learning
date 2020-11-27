package com.interface_demo;

// 如果不想重写 则在 class 前使用 abstract 去修饰

public class Dog implements Jumpping {
    @Override
    public void jump() {
        System.out.println("狗在跳高");
    }
}
