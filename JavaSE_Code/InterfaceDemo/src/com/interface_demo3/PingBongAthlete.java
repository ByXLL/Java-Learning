package com.interface_demo3;

/**
 * 兵乓球 运动员类
 */
public class PingBongAthlete extends Athlete implements SpeakEnglish {
    public PingBongAthlete() {
    }

    public PingBongAthlete(String name, int age) {
        super(name, age);
    }

    @Override
    public void run() {
        System.out.println("兵乓球运动员: "+ getName()+" 在训练");
    }

    @Override
    public void eat() {
        System.out.println("兵乓球运动员: "+ getName()+" 在吃饭");
    }

    @Override
    public void speak() {
        System.out.println("兵乓球运动员: "+ getName()+" 在说英语");
    }

    public void doSomeThing() {
        System.out.println(getName() + " 去做一些别的事情");
    }
}
