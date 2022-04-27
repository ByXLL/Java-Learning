package com.brodog.thread;

/**
 * 守护线程
 * @author By-Lin
 */
@SuppressWarnings("all")
public class DaemonThread {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("子线程执行----是否是守护线程---" + Thread.currentThread().isDaemon());
            // 模拟一直执行
            while (true) {

            }
        });
        // 默认是用户线程 当用户线程还在执行的时候，虽然主线程关闭了，但是JVM还是在运行者用户线程

        // 如果将当前线程设置为守护线程，则主线程执行完成了，而且剩下的这个也是守护线程，这个时候JVM就会关闭
        thread.setDaemon(true);

        thread.start();

        System.out.println("主线程关闭了");
    }
}
