package com.interface_demo3;

/**
 * 篮球教练类
 */
public class BasketBallInstructor extends Instructor {
    public BasketBallInstructor() {
    }

    public BasketBallInstructor(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("篮球教练: "+ getName()+" 教怎么打篮球");
    }

    @Override
    public void eat() {
        System.out.println("篮球教练: "+ getName()+" 在吃饭");
    }
}
