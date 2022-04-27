package com.brodog.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 使用线程进行批量执行
 * 阿里巴巴规范禁止使用这种方法
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // 频繁创建线程 耗费资源 造成执行缓慢
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    list.add(random.nextInt());
                }
            };
            thread.start();
            thread.join();
        }
        System.out.println("花费时间： " + (System.currentTimeMillis() - startTime));
        System.out.println("大小： " + list.size());
    }
}
