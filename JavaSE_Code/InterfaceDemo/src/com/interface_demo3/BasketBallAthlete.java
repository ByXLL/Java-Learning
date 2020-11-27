package com.interface_demo3;

/**
 * 篮球运动员类
 */
public class BasketBallAthlete extends Athlete {
    public BasketBallAthlete() {
    }

    public BasketBallAthlete(String name, int age) {
        super(name, age);
    }

    @Override
    public void run() {
        System.out.println("篮球运动员: "+ getName()+" 教怎么打篮球");
    }

    @Override
    public void eat() {
        System.out.println("篮球运动员: "+ getName()+" 在吃饭");
    }
}
