package com.interface_demo;

// 接口是实现 使用的是 implements  而类是继承  二者都需要去实现 抽象方法
public class Cat implements Jumpping {
    @Override
    public void jump() {
        System.out.println("猫在跳高");
    }
}
