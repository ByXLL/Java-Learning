package com.demo_01;

public class ArrayDemo {
    public static void main(String[] args) {
        // 数组的动态初始化
        int[] intArry = new int[10];

        // 数组的静态初始化
        int[] intArry2 = new int[]{1,2,3};
        // 简化格式
        int[] intArry3 = {1,2,3};


        // 数组反转
        int[] intArray = {1,2,3,4,5,6,7,8,9};
        for (int start = 0, end = intArray.length-1; start <= end; start++,end--) {
            int intTemp = intArray[start];
            intArray[start] = intArray[end];
            intArray[end] = intTemp;
        }
        System.out.println(intArray);
    }
}
