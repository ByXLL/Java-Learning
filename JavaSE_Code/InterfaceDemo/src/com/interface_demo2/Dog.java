package com.interface_demo2;

public class Dog extends Animal implements Jumpping {
    public Dog() {
    }

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃骨头");
    }

    public void showInfo() {
        System.out.println(getName() + " 是一条狗  今年 "+ getAge() + " 岁了");
    }

    @Override
    public void jump() {
        System.out.println(getName() + " 在跳高");
    }
}
