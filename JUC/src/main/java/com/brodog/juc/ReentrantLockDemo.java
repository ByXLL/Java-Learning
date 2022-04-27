package com.brodog.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ReentrantLockDemo {

    // 可重入锁 递归调用
    public synchronized void add() {
        add();
    }
    public static void main(String[] args) {
        // 递归 会出现栈溢出
//        new ReentrantLock().add();

        // 使用 synchronized
        Object o = new Object();
        new Thread(()->{
            synchronized (o) {
                System.out.println(Thread.currentThread().getName()+"------第一层");
                synchronized (o) {
                    System.out.println(Thread.currentThread().getName()+"------第二层");
                    synchronized (o) {
                        System.out.println(Thread.currentThread().getName()+"------第三层");
                    }
                }
            }
        },"T1").start();

        // 使用Lock
        Lock lock = new ReentrantLock();
        new Thread(()->{
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"------第一层");
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName()+"------第二层");
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName()+"------第三层");
                    }finally {
                        lock.unlock();
                    }
                }finally {
                    lock.unlock();
                }
            }finally {
                lock.unlock();
            }
        },"T1").start();

    }
}
