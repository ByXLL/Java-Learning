package com.customer_demo;

public class Producer implements Runnable {

    private Box box;

    public Producer(Box box) {
        this.box = box;
    }

    @Override
    public void run() {
        for (int i = 1; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 生产牛奶
            box.put(i);
        }
    }
}
