package com.brodog.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池进行批量执行
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        // 创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100000; i++) {
            // 使用线程池的时候 并没有频繁创建线程
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            });
        }
        // 关闭线程池
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("花费时间： " + (System.currentTimeMillis() - startTime));
        System.out.println("大小： " + list.size());
    }
}
