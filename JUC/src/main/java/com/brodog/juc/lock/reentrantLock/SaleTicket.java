package com.brodog.juc.lock.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用重入锁进行 卖票
 * @author By-Lin
 */
@SuppressWarnings("all")
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 创建多个线程
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale(1);
            }
        }, "T1").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale(1);
            }
        }, "T2").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale(1);
            }
        },  "T3").start();
    }
}

class Ticket{
    private int count = 30;

    // 创建重入锁
    private final ReentrantLock lock = new ReentrantLock();

    // 加锁 卖票
    public void sale(int count) {
        // 手动上锁
        lock.lock();
        try {
            if(this.count >= count) {
                this.count -= count;
                System.out.println("线程--" + Thread.currentThread().getName() + "买 " + count +" 张票---还剩下 " + this.count +" 张票");
            }else {
                System.out.println("票卖完了");
            }
        } finally {
            lock.unlock();
        }
    }
}