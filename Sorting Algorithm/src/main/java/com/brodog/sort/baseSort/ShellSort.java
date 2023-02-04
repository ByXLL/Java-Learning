package com.brodog.sort.baseSort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author By-Lin
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {10,3,4,10,11,7,2};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        // 根据数组的长度，确定增长量的初始值
        int h = 1;
        while (h<arr.length / 2) {
            h = 2 * h + 1;
        }

        // 进行希尔排序
        while (h >= 1) {
            // 排序
            // 找到待插入的元素
            for (int i = h; i < arr.length; i++) {
                // 将待插入元素插入到有序的序列中
                for (int j = i; j >= h; j-=h) {
                    // 待插入元素是 arr[j]，比较a[j]和a[j-h]，
                    if(arr[j-h] > arr[j]) {
                        // 交换位置
                        int temp = arr[j-h];
                        arr[j -h] = arr[j];
                        arr[j] = temp;
                    }else {
                        // 待插入元素已经找到了合适位置，结束循环
                        break;
                    }
                }
            }
            // 减少h
            h = h / 2;
        }
    }
}
