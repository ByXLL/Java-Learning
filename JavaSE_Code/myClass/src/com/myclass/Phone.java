package com.myclass;

public class Phone {
    // 成员变量
    String brand;
    int price;

    // 成员方法
    public void call() {
        System.out.println("打电话");
    }

    public void sendMessage() {
        System.out.println("发短信");
    }

    public void showAllInfo() {
        System.out.println("手机品牌是: "+ brand);
        System.out.println("手机价格是: "+ price +" 元");
    }
}
