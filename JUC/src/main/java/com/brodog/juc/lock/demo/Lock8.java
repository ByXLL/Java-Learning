package com.brodog.juc.lock.demo;

import java.util.concurrent.TimeUnit;

/**
 * 锁的八个问题
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        /**
         * 1、标准访问
         * 2、先执行的线程中执行的方法中延时4秒
         * ---------发送消息
         * ---------发送邮件*
         *
         * 因为两个线程操作同一个资源，而两个方法都是有 synchronized 锁的，这个时候会等待锁释放
         * 而先执行的先获取到了锁，第二个就得等待，无论第一个执行了多久都会等
         * 因为这种情况下，synchronized 锁的是当前对象实例 this
         *
         */
//        new Thread(()-> {
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        // 为了前面的先执行
//        Thread.sleep(100);
//
//        new Thread(()-> {
//            try {
//                phone.sendEmail();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();


        /**
         * 3、先调用有锁有延时的的，再调没锁的
         * ---------hello
         * ---------发送消息
         *
         *
         */
//        new Thread(()-> {
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        // 为了前面的先执行
//        Thread.sleep(100);
//
//        new Thread(()-> {
//            try {
//                phone.hello();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();


        /**
         * 4、有两个相同的资源，执行
         */
        new Thread(()-> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(100);

        new Thread(()-> {
            try {
                phone.hello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }
}