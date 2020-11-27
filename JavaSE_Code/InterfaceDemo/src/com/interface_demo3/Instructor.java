package com.interface_demo3;

/**
 * 抽象 教练类  继承 抽象人类
 */
public abstract class Instructor extends Person {
    public Instructor() {
    }

    public Instructor(String name, int age) {
        super(name, age);
    }

    public abstract void teach();
}
