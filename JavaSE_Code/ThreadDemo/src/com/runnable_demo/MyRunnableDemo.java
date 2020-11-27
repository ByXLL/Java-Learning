package com.runnable_demo;

public class MyRunnableDemo {
    public static void main(String[] args) {
        // 创建MyRunnable 类的对象
        MyRunnable myRunnable = new MyRunnable();

        // 创建Thread类的对象，把 MyRunnable 对象作为构造方法的参数
        Thread thread1 = new Thread(myRunnable, "高铁");
        Thread thread2 = new Thread(myRunnable, "飞机");
        Thread thread3 = new Thread(myRunnable, "跑车");

        // 启动线程
        thread1.start();
        thread2.start();
        thread3.start();

    }
}
