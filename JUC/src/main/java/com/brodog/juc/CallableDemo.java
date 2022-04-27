package com.brodog.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable 接口demo
 * @author By-Lin
 */
@SuppressWarnings("all")
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 使用 Runnable 接口创建线程
        new Thread(new MyThread1(),"T1").start();

        // 使用 Callable 接口创建线程 但是不能像使用Runnable接口一样直接new一个实例，这个时候，我们需要找一个中间者 Clallable接口的实现类FutureTask 类
        FutureTask<Object> futureTask = new FutureTask<Object>(new MyThread2());
        new Thread(futureTask,"T2").start();
        Object result1 = futureTask.get();
        System.out.println("Callble接口返回值---"+ result1);

        // lam表达式
        FutureTask<Object> futureTask1 = new FutureTask<>(()->{
            System.out.println(Thread.currentThread().getName() +  "执行");
            return 200;
        });
        new Thread(futureTask1,"T3").start();
        Object result2 = futureTask1.get();
        System.out.println("Callble接口返回值---"+ result2);

    }
}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +  "执行");
    }
}

class MyThread2 implements Callable {
    @Override
    public Object call() {
        System.out.println(Thread.currentThread().getName() +  "执行");
        return 200;
    }
}
