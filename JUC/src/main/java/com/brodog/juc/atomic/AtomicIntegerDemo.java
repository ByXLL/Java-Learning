package com.brodog.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决频繁需要加锁操作的更高效的方法，使用AtomXXX类
 * AtomXXX类本身方法都是原子性的，但!不能保证多个方法连续调用是原子性的
 *
 * 原来我们使用 volatile 当我们使用 count++ 的话这个时候是不具备原子性的，我们该用Atomic的原子类
 * @author By-Lin
 */
@SuppressWarnings("all")
public class AtomicIntegerDemo {
//    volatile int count = 0;
    AtomicInteger count = new AtomicInteger(0);

    void m() {
        for (int i = 0; i < 10000; i++) {
            // 线程安全的 count++
            count.incrementAndGet();
//            count++;
        }
    }

    public static void main(String[] args) {
        AtomicIntegerDemo data = new AtomicIntegerDemo();

        Optional<String> optional = Optional.ofNullable("a");
        optional.get();
        optional.isPresent();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(data::m,"T"+1));
        }

        threads.forEach((item)-> { item.start(); });

        threads.forEach((item) ->{
            try {
                item.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(data.count);
    }
}
