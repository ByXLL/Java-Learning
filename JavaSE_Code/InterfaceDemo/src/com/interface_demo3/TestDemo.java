package com.interface_demo3;

public class TestDemo {
    public static void main(String[] args) {
        // 创建一个兵乓球运动员实例
        PingBongAthlete zhangjike = new PingBongAthlete("张继科",35);
        zhangjike.eat();
        zhangjike.run();
        zhangjike.speak();
        zhangjike.doSomeThing();

        System.out.println("--------------------");
        // 创建一个篮球运动员实例
        BasketBallAthlete yaoming = new BasketBallAthlete();
        yaoming.setName("姚明");
        yaoming.setAge(40);
        yaoming.eat();
        yaoming.run();

        System.out.println("--------------------");

    }
}
