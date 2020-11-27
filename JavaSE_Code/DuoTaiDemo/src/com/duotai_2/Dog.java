package com.duotai_2;

public class Dog extends Animal {
    public String name = "柴犬";

    public String getName() {
        return name;
    }

    @Override
    public void eat() {
        System.out.println("狗吃骨头");
    }

    public void watchDoor() {
        System.out.println("狗看门");
    }
}
