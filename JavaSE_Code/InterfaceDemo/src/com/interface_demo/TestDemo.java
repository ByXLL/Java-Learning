package com.interface_demo;

public class TestDemo {
    public static void main(String[] args) {
        // 接口的实例化 参照 抽象实例化
        Jumpping cat = new Cat();
        cat.jump();


        Jumpping dog = new Dog();
        dog.jump();
    }
}
