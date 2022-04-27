package com.brodog.thread;

import java.util.concurrent.*;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 这几个创建的类 都是调用 ThreadPoolExecutor 构造函数 其实本质上只是参数上不一样而已
        ExecutorService executorService1 = Executors.newCachedThreadPool();  // 快
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);    // 慢
        ExecutorService executorService3 = Executors.newSingleThreadExecutor(); // 最慢


        // new ThreadPoolExecutor()
        /**
         * 使用 ThreadPoolExecutor 进行自定义配置线程池
         * arg1     corePoolSize        核心线程数
         * arg2     maximumPoolSize     非核心线程数
         * arg3     keepAliveTime       时间
         * arg4     unit                时间单位
         * arg5     workQueue           队列          当线程池中全局线程都被使用了 这个时候添加进来的任务 先去排队 将会存放到这里
         * arg6     threadFactory       线程工厂
         * arg7     handler             拒绝策略
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));

        for (int i = 0; i < 100; i++) {
//            executorService3.execute(new MyTask(i+1));
            threadPoolExecutor.execute(new MyTask(i+1));
        }
    }
}
class MyTask implements Runnable {
    int i = 0;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("线程名称： " + Thread.currentThread().getName() + " ---- " + i);
        try {
            // 模拟业务处理时间
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}