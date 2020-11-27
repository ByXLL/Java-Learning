package com.chouxiang;

// 抽象类的子类 要么重写父类的方法  要么转换成抽象类 在 class 前加入 abstract 关键字
public class Cat extends Animal {
    public Cat() {
    }

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " 吃的东西是 鱼");
    }
}
