package com.brodog.juc.volatiledemo;

import java.util.concurrent.TimeUnit;

/**
 * volatile 可见性体验
 * 在多线程下修改同一个全局变量 默认情况下一个线程修改了其他线程是无法感知到这个变量的变化的
 * 因为在一个线程创建的时候去拷贝一份副本在当前线程内部使用 实际上修改的就是当前线程内部的
 * @author By-Lin
 */
@SuppressWarnings("all")
public class VolatileVisibilityDemo {
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("------ 等待修改 -------");
            while (!flag) {}
            System.out.println("--------- flag修改了 ---------");
        }).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(()->{
            System.out.println("------ 开始修改标识 -------");
            flag = true;
            System.out.println("------ 修改标识完成 -------");
        }).start();
    }
}
