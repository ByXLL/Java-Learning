package com.brodog.juc.sync;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 创建多个线程
        new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                ticket.sale(1);
            }
        }, System.currentTimeMillis() + "").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale(1);
            }
        }, System.currentTimeMillis() + "").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.sale(1);
            }
        }, System.currentTimeMillis() + "").start();
    }
}

class Ticket{
    private int count = 30;

    // 加锁 卖票
    public synchronized void sale(int count) {
        if(this.count >= count) {
            this.count -= count;
            System.out.println("线程--" + Thread.currentThread().getName() + "买 " + count +" 张票---还剩下 " + this.count +" 张票");
        }else {
            System.out.println("票卖完了");
        }
    }
}