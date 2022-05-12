package com.brodog.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author By-Lin
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {10,3,4,10,11,7,2};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        // 如果在某一次冒泡排序过程中，没有交换元素，则说明该数组已经是有序的
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {  // 冒泡的次数
            // 冒泡的步骤
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j+1]) {
                    flag = false;
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j +1] = temp;
                }
            }
            // 如果有序，则直接跳出循环
            if(flag) { break; }
        }
    }
}
