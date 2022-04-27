package com.brodog.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * juc 辅助类 CyclicBarrier
 * 模拟场景 集齐七颗龙珠召唤神龙
 * @author By-Lin
 */
@SuppressWarnings("all")
public class CyclicBarrierDemo {
    // 设置固定值
    private static final int NUMBER = 7;

    public static void main(String[] args) {
        // 创建CyclicBarrier（步骤数, Runable接口-到达目标值处理方法）
        // 如果达不到目标值，则会一直阻塞等待
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER,()->{
            System.out.println("集齐七颗龙珠了，开始召唤神龙.....");
        });

        // 多线程收集龙珠
        for (int i = 1; i < 8; i++) {
            new Thread(()->{
                try {
                    System.out.println("第 " + Thread.currentThread().getName() + " 颗龙珠已经被收集到了");
                    // 等待 收集完七颗
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },i + "").start();
        }

    }
}
