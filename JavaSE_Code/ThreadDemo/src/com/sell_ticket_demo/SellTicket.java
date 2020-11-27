package com.sell_ticket_demo;

public class SellTicket implements Runnable {
    private static int tickets = 100;
    private Object obj = new Object();

    private int x = 0;
    @Override
    public void run() {
        while (true) {
            if(x%2 ==0) {
                // 通过加锁的形式  使得代码同步
//                synchronized (obj) {
                // 如果是非静态方法 可以使用 this
//                synchronized (this) {
                // 静态方法需要使用 类的字节码对象
                synchronized (SellTicket.class) {
                    if(tickets > 0) {
                        // 通过sleep 方法来模拟出票时间
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "  正在出售第 " + tickets +" 张票");
                        tickets--;
                    }
                }
            }else {
//                // 通过加锁的形式  使得代码同步
//                synchronized (obj) {
//                    if(tickets > 0) {
//                        // 通过sleep 方法来模拟出票时间
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(Thread.currentThread().getName() + "  正在出售第 " + tickets +" 张票");
//                        tickets--;
//                    }
//                }
                sellTicket();
            }
            x++;
        }
    }

    // 同步方法 把 synchronized 加到 方法上
    // 同步 非静态 方法 的锁的对象是this
    private static synchronized void  sellTicket() {
        if(tickets > 0) {
            // 通过sleep 方法来模拟出票时间
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "  正在出售第 " + tickets +" 张票");
            tickets--;
        }
    }


//    private void sellTicket() {
//        // 通过加锁的形式  使得代码同步
//        synchronized (obj) {
//            if(tickets > 0) {
//                // 通过sleep 方法来模拟出票时间
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "  正在出售第 " + tickets +" 张票");
//                tickets--;
//            }
//        }
//    }
}
