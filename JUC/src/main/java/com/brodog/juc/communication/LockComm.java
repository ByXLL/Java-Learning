package com.brodog.juc.communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 同步锁 线程间通知
 * 对一个变量进行操作，当变量为0时+1，当变量为1时-1
 * @author By-Lin
 */
@SuppressWarnings("all")
public class LockComm {
    public static void main(String[] args) {
        LockShare lockShare = new LockShare();

        // 执行 +1 操作
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lockShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T1").start();

        // 执行 -1 操作
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lockShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T2").start();

        // 执行 +1 操作
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lockShare.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T3").start();

        // 执行 -1 操作
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    lockShare.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "T4").start();
    }
}

class LockShare {
    // 初始值
    private int number = 0;

    // 创建锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // +1的方法
    public void incr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            while (this.number != 0) {
                // 等待
                condition.await();
            }
            // 干活 当等于0的时候 对number +1
            number++;
            System.out.println("线程--- " + Thread.currentThread().getName() + " 执行了+1操作----" + this.number);
            // 通知
            condition.signalAll();
        }finally {
            // 解锁
            lock.unlock();
        }
    }

    // -1的方法
    public void decr() throws InterruptedException {
        // 上锁
        lock.lock();
        try {
            // 正确方式
            while (this.number != 1) {
                condition.await();
            }
            // 干活 当等于0的时候 对number -1
            number--;
            System.out.println("线程--- " + Thread.currentThread().getName() + " 执行了-1操作----" + this.number);
            // 通知
            condition.signalAll();
        }finally {
            // 解锁
            lock.unlock();
        }
    }
}
