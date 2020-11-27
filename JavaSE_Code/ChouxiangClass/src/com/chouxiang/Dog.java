package com.chouxiang;

public class Dog extends Animal {

    public Dog() {
    }

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() +" 吃的东西是 骨头");
    }
}
