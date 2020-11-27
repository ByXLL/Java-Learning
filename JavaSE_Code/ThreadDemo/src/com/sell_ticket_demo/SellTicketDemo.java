package com.sell_ticket_demo;

public class SellTicketDemo {
    public static void main(String[] args) {
        // 创建SellTicket类的对象
        SellTicket sellTicket = new SellTicket();

        // 创建三个Thread类的对象，把SellTicket对象做为构造方法的参数，并给出对应的窗口名称
        Thread thread1 = new Thread(sellTicket,"窗口一");
        Thread thread2 = new Thread(sellTicket,"窗口二");
        Thread thread3 = new Thread(sellTicket,"窗口三");


        thread1.start();
        thread2.start();
        thread3.start();
    }
}
