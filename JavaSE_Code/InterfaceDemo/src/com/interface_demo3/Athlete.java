package com.interface_demo3;
/*
  抽象 运动员类 继承 抽象人 类
 */
public abstract class Athlete extends Person {
    public Athlete() {
    }

    public Athlete(String name, int age) {
        super(name, age);
    }

    public abstract void run();
}
