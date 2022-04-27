package com.brodog.juc;

import java.util.concurrent.CountDownLatch;

/**
 * juc 辅助类 CountDownLatch
 * 场景 5个同学 全部离开教室才关门
 * @author By-Lin
 */
@SuppressWarnings("all")
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 基本写法  会有问题 不知道到底还有没有人就关门了
//        for (int i = 0; i < 5; i++) {
//            new Thread(()->{
//                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
//            },String.valueOf(i+1)).start();
//        }
//        System.out.println("关门了");


        // 使用 CountDownLatch 写法
        // 1、创建 CountDownLatch 对象，设置初始值
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " 号同学离开了教室");
                // 2、计数器减一
                countDownLatch.countDown();
            },String.valueOf(i+1)).start();
        }

        // 3、阻塞等待
        countDownLatch.await();
        System.out.println("关门了");

    }
}
