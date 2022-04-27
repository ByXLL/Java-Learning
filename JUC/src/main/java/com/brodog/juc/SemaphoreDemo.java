package com.brodog.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * juc 辅助类 Semaphore
 * 模拟案例 6辆车停到3个车位里去
 * @author By-Lin
 */
@SuppressWarnings("all")
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 创建Semaphore 设置许可数量
        Semaphore semaphore = new Semaphore(3);

        // 创建6个线程 模拟6辆车
        for (int i = 1; i < 7; i++) {
            new Thread(()->{
                try {
                    // 抢占车位
                    semaphore.acquire();
                    System.out.println("第 " + Thread.currentThread().getName() +" 辆车抢到了车位");

                    // 设置随机停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("第 " + Thread.currentThread().getName() +" 辆车离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放 释放车位
                    semaphore.release();
                }
            },i+"").start();
        }
    }
}
