package com.duotai;

public class Dog extends Animal {
    public int age = 5;
    public int weight = 1;

    @Override
    public void eat() {
        // 重写父类的 方法
        System.out.println("狗吃骨头");
    }

    public void wangWang() {
        System.out.println("狗汪汪汪");
    }
}
