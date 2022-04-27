package com.brodog.juc.lock.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author By-Lin
 */
class Phone {
    public synchronized void sendSms() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("---------发送消息");
    }
    public synchronized void sendEmail() {
        System.out.println("---------发送邮件");
    }

    public void hello() {
        System.out.println("---------hello");
    }
}