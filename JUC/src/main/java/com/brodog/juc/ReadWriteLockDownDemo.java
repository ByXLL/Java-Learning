package com.brodog.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁降级 案例
 * @author By-Lin
 */
public class ReadWriteLockDownDemo {
    public static void main(String[] args) {
        // 创建 可重入读写锁
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        // 锁降级  在写的时候也可以读
        // 1、先获取到写锁
        writeLock.lock();
        System.out.println("开始写了.....");
        // 2、获取到读锁
        readLock.lock();
        System.out.println("开始读......");
        // 3、释放写锁
        writeLock.unlock();

        // 4、释放读锁
        readLock.unlock();

        /**
         * 输出
         * 开始写了.....
         * 开始读......
         * 证明了在获取到写锁的时候还是可以读的
         */


        // 证明无法升级锁
        // 获取读锁
        readLock.lock();
        System.out.println("开始读......");

        // 获取写锁
        writeLock.lock();
        System.out.println("开始写了......");

        /**
         * 无法进行锁的升级 无法从读锁升级为写锁
         * 输出 并一直等待锁的释放
         * 开始读......
         */
    }
}
