package com.demo_01;

public class Test01 {
    public static void main(String[] args) {
        for (int i = 7; i < 100; i++) {
            if(i%10 ==7 || i/10%10 == 7 || i%7 == 0) {
                System.out.println(i);
            }
        }

        // 这里跟前端不一样  因为类型为int 71/10 == 7  而前端是7.1
//        int data = 71;
//        int data2 = data/10;
//        int data3 = data2%10;
//        System.out.println(data3 == 7);
    }
}
