package com.brodog.thread;

/**
 * @author By-Lin
 */
public class ThreadDemo extends Thread{
    private String name;

    public ThreadDemo(String name) {
        this.name = name;
    }

    /**
     * 复写run方法，自定义运行逻辑
     */
    @Override
    public void run() {
        System.out.println(name);
    }

    /**
     * 线程是有生命周期的 当通过start进行启动线程才会进入线程的生命周期，这样才是使用了线程执行，
     * 如果是run的话，只是使用了内部方法，就是主线程进行方法调用而已
     */
    public static void main(String[] args) {
        // 使用run方法 至始至终都是主线程在执行  这种就是方法调用
        new ThreadDemo("11111").run();
        // 而使用start方法则是开启了线程 当现在执行完成才关闭主线程
        new ThreadDemo("22222").start();
    }
}
