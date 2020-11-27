package com.duotai_2;

public class Cat extends Animal {
    public String name = "蓝猫";

    public String getName() {
        return name;
    }

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}
