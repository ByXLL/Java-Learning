package com.thread_demo;

/**
 * 方式一：继承Thread类
 *  1.定义一个类 去继承 Thread 类
 *  2.重写 run方法
 *  3.创建 定义的 MyThread 类的对象
 *  4.启动线程
 */
public class MyThreadDemo {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        // 给线程设置名字
        myThread1.setName("飞机");
        myThread2.setName("跑车");
        myThread3.setName("高铁");



        // 获取当前执行的线程的名字
        System.out.println("主线程名字：  " + Thread.currentThread().getName());
        System.out.println("主线程ID：  " + Thread.currentThread().getId());

        // 获取线程的优先级  默认是5 最小是1 最大是10
        System.out.println("线程一的优先级：  " + myThread1.getPriority());
        System.out.println("线程二的优先级：  " + myThread2.getPriority());
        System.out.println("线程三的优先级：  " + myThread3.getPriority());

        myThread1.setPriority(1);
        myThread1.setPriority(5);
        myThread1.setPriority(10);


        // 启用线程
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
