package com.brodog.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture 异步回调
 * @author By-Lin
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws Exception {
        // 异步调用  没有返回值
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "----异步调用");
        });

        // 获取结果
        completableFuture1.get();

        // 异步调用  有返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "----异步调用");
            return 100;
        });
        // 获取结果
        completableFuture2.whenComplete((t,u)->{
            // 返回值
            System.out.println("-----t-----" + t);

            // 异常
            System.out.println("-----u-----" + u);
        }).get();



    }
}
