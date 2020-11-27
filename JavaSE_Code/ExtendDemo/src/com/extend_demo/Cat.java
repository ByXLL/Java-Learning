package com.extend_demo;

public class Cat extends Animal {
    public Cat() { }

    public Cat(String name, int age) {
        super(name, age);
    }

    public void catchMouse() {
        System.out.println("捉老鼠");
    }
}
