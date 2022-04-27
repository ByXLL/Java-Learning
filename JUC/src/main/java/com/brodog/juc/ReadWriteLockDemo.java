package com.brodog.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁 demo
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        MyCache2 myCache2 = new MyCache2();

        // 创建线程往里存数据
        for (int i = 1; i < 3; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num+"",num+"");
//                myCache2.put(num+"",num+"");
            },i+"").start();
        }

        // 创建线程获取数据
        for (int i = 1; i < 3; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
//                myCache2.get(num+"");
            },i+"").start();
        }
    }
}

// 没有使用读写锁有问题
@SuppressWarnings("all")
class MyCache {
    private volatile Map<String,Object> map = new HashMap<>();

    // 添加
    public void put(String key, Object value)  {
        System.out.println(Thread.currentThread().getName() + " 正在执行写操作 ---" + key);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName() + " 写操作执行完成 ---" + key);
    }

    // 获取数据
    public Object get(String key) {
        System.out.println(Thread.currentThread().getName() + " 正在执行获取操作");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName() + " 读取操作执行完成 ---" + key);
        return result;
    }
}

// 使用读写锁
@SuppressWarnings("all")
class MyCache2 {
    private volatile Map<String,Object> map = new HashMap<>();

    // 创建读写锁对象
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // 添加
    public void put(String key, Object value){
        // 添加写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行写操作 ---" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + " 写操作执行完成 ---" + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            readWriteLock.writeLock().unlock();
        }

    }

    // 获取数据
    public Object get(String key) {
        // 添加读锁
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在执行获取操作");
            TimeUnit.SECONDS.sleep(1);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取操作执行完成 ---" + key);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }
        return null;
    }
}

