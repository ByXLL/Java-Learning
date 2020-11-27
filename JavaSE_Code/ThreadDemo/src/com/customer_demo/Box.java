package com.customer_demo;

public class Box {
    private int numeber;

    private boolean hasMilk = false;

    // 送奶工将牛奶放到盒子里  wait 要和 synchronized 一起使用
    public synchronized void put(int numeber) {
        // 如果有牛奶 则等待消费者
        if(hasMilk) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 生产牛奶
        this.numeber = numeber;
        System.out.println("送奶工将第 "+ this.numeber +" 牛奶放到盒子里");

        // 修改状态
        this.hasMilk = true;

        // 唤醒其他线程
        notifyAll();
    }

    // 用户拿到第几瓶奶
    public synchronized void get() {
        // 如果没有牛奶 则等待生产
        if(!hasMilk) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 消费
        System.out.println("用户拿到第 "+ this.numeber+" 瓶奶");

        this.hasMilk = false;

        // 唤醒其他线程
        notifyAll();
    }
}
