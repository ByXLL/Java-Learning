package com.brodog.juc.volatiledemo;

/**
 * 非原子性 案例
 * 按我们的意愿10个线程，每个线程累加线程累加1000，一共是10 * 1000=10000。
 * 但是volatile int num = 0; 使用volatile与否都是体现非原子性，运行的结果都比10000小：
 * @author By-Lin
 */
@SuppressWarnings("all")
public class VolatileAtomicityDemo {
    public static void main(String[] args) {
        final TestData testData = new TestData();
        for(int i = 1; i <= 10; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    testData.updateNum();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("最终结果：" + testData.num);
    }
}

class TestData {
    // 这里 volatile 修饰的是变脸
    volatile int num = 0;
    // 为了达到原子性效果 可以使用 synchronized
    public void updateNum(){
        // 但是操作并不是原子性操作
        num++;
    }
}