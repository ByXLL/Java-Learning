package com.lock_demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SellTicket implements Runnable {
    private static int tickets = 100;
    private Object obj = new Object();
    private int x = 0;

    // 使用 lock 解决线程不安全问题

    // 先new 一个lock 接口的 的实现类对象
    // void lock() 加锁
    // void unlock() 解锁
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            // 加上try 解决在执行的时候出问题 无法关锁
            try {
                // 在代码处 加锁
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "  正在出售第 " + tickets + " 张票");
                    tickets--;
                }else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }

    }
}
