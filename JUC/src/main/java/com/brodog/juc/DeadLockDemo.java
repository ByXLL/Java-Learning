package com.brodog.juc;

import java.util.concurrent.TimeUnit;

/**
 * 死锁
 * @author By-Lin
 */
@SuppressWarnings("all")
public class DeadLockDemo {

    static Object a = new Object();
    static Object b = new Object();


    public static void main(String[] args) {
        // 模拟在A中尝试获取B锁，B中尝试获取A锁
        new Thread(()->{
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() +" 持有A的锁，尝试获取B的锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() +" 获取到B的锁");
                }
            }
        },"T1").start();

        new Thread(()->{
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() +" 持有B的锁，尝试获取A的锁");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() +" 获取到A的锁");
                }
            }
        },"T2").start();
    }
}
