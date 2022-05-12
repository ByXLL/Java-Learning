package com.brodog.sort;

import java.util.Arrays;

/**
 * 选择索引
 * @author By-Lin
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {10,3,4,10,11,7,2};
        selection(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 定义一个变量，记录最小值所在的索引，默认数组的第一个元素所在的索引
            int minIndex = i;
            for (int j = i+1; j < arr.length ; j++) {
                // 需要比较最小索引和 j 索引的值，如果j的值小，则将最小值的索引改为j
                if(arr[minIndex] > arr[j]) { minIndex = j; }
            }

            // 拿到新的最小值所在的索引，去交换默认最小值索引上的值和新的最小值索引上的值
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }
}
