package com.apis;

import java.util.Arrays;

public class ChangeDemo {
    public static void main(String[] args) {
        // 需求 将 “91 27 46 38 50” 排序 并输出 “27 38 46 50 91”
        String s1 = "91 27 46 38 50";

        // 将字符串 按 “ ” 截取 成一个 String 数组
        String[] strArr = s1.split(" ");

        // 定义一个 int 数组
        int[] intArr = new int[strArr.length];

        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        // 对数组排序
        Arrays.sort(intArr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intArr.length; i++) {
            if(i == intArr.length -1 ) {
                sb.append(intArr[i]);
            }else {
                sb.append(intArr[i]).append(" ");
            }
        }

        // 输出
        System.out.println(sb);

    }
}
