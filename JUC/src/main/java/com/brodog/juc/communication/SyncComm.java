package com.brodog.juc.communication;

/**
 * Synchronized 同步锁 线程间通知
 * 对一个变量进行操作，当变量为0时+1，当变量为1时-1
 * @author By-Lin
 */
@SuppressWarnings("all")
public class SyncComm {
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

class Share {
    // 初始值
    private int number = 0;

    // +1的方法
    public synchronized void incr() throws InterruptedException {
//        // 判断 错误方式
//        if(this.number != 0) {
//            // 等待  但是如果进入了等待，下次醒来就会从这里执行    就会造成一个虚假唤醒
//            // 官方文档中要求此方法应始终再循环中使用
//            this.wait();
//        }

        // 正确方式
        while (this.number != 0) {
            this.wait();
        }


        // 干活 当等于0的时候 对number +1
        number++;

        System.out.println("线程--- " + Thread.currentThread().getName() + " 执行了+1操作----" + this.number);

        // 通知
        this.notifyAll();
    }

    // -1的方法
    public synchronized void decr() throws InterruptedException {
        // 错误方式
//        // 判断
//        if(this.number != 1) {
//            // 等待  但是如果进入了等待，下次醒来就会从这里执行    就会造成一个虚假唤醒
//            // 官方文档中要求此方法应始终再循环中使用
//            this.wait();
//        }

        // 正确方式
        while (this.number != 1) {
            this.wait();
        }
        // 干活 当等于0的时候 对number -1
        number--;

        System.out.println("线程--- " + Thread.currentThread().getName() + " 执行了-1操作----" + this.number);

        // 通知
        this.notifyAll();
    }
}
