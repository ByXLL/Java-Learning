package com.interface_demo3;

/**
 * 兵乓球 教练类
 */
public class PingBongInstructor extends Instructor implements SpeakEnglish {
    public PingBongInstructor() {
    }

    public PingBongInstructor(String name, int age) {
        super(name, age);
    }

    @Override
    public void teach() {
        System.out.println("兵乓球教练: "+ getName()+" 教怎么打兵乓球");
    }

    @Override
    public void eat() {
        System.out.println("兵乓球教练: "+ getName()+" 在吃饭");
    }

    @Override
    public void speak() {
        System.out.println("兵乓球教练: "+ getName()+" 在说英语");
    }
}
