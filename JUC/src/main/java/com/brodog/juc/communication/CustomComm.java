package com.brodog.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定制化通信
 * 启动三个线程，按照如下要求
 * T1打印5次  T2打印10次  T3打印15次
 * 并且重复10次
 * @author By-Lin
 */
@SuppressWarnings("all")
public class CustomComm {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        // 创建线程1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print1(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T1").start();

        // 创建线程1
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print2(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T2").start();

        // 创建线程3
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    shareResource.print3(i+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"T3").start();
    }

}

@SuppressWarnings("all")
class ShareResource {
    // 定义标识位
    private int flag = 1;

    // 创建lock锁
    private Lock lock = new ReentrantLock();

    // 创建3个 Condition 类似于3把钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    // 打印5次
    public void print1(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                // 不是线程一 等待
                condition1.await();
            }
            // 干活
            System.out.println(Thread.currentThread().getName() +  " ---执行线程一的第 "+ loop +" 轮任务------" + 1);
            // 修改标识
            this.flag = 2;
            // 通知第二个线程
            condition2.signal();
        }finally {
            // 释放锁
            lock.unlock();
        }
    }

    // 打印2次
    public void print2(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (this.flag != 2) {
                // 等待
                condition2.await();
            }
            // 干活
            for (int i = 1; i < 3; i++) {
                System.out.println(Thread.currentThread().getName() +  " ---执行线程二的第 "+ loop +" 轮任务------" + i);
            }
            // 修改标识
            this.flag = 3;
            // 通知线程3
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    // 打印3次
    public void print3(int loop) throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 判断
            while (this.flag != 3) {
                // 等待
                condition3.await();
            }
            // 干活
            for (int i = 1; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() +  " ---执行线程三的第 "+ loop +" 轮任务------" + i);
            }

            // 修改标识
            this.flag = 1;

            // 通知线程1
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }

}