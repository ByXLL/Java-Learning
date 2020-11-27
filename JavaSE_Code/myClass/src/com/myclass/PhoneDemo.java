package com.myclass;

public class PhoneDemo {
    public static void main(String[] args) {
        // 创建对象
        Phone phone = new Phone();

        System.out.println(phone.brand);
        System.out.println(phone.price);

        phone.brand = "苹果";
        phone.price = 9999;

        System.out.println(phone.brand);
        System.out.println(phone.price);

        phone.call();
        phone.sendMessage();
        phone.showAllInfo();
    }
}
