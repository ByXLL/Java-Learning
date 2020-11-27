package com.runnable_demo;

/***
 * 方式二：实现一个Runnable接口
 *  1.定义一个类 MyRunnable 实现Runnable 接口
 *  2.在MyRunnable 类中重写 run 方法
 *  3.创建 MyRunnable 类的对象
 *  4.创建Thread类的对象，把 MyRunnable 对象作为构造方法的参数
 *  5.启动线程
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "  " + i);
        }
    }
}
