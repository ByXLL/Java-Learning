package com.interface_demo2;

public class Cat extends Animal implements Jumpping {
    public Cat() {
    }

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() +" 吃鱼");
    }

    public void showInfo() {
        System.out.println(getName() + " 是一只猫 今年"+ getAge() + " 岁了");
    }

    @Override
    public void jump() {
        System.out.println(getName() + " 在跳高");
    }
}
