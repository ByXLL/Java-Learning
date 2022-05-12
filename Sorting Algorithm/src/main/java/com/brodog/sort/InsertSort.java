package com.brodog.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @author By-Lin
 */
public class InsertSort {
    public static void main(String[] args) {

        int[] arr = {10,3,4,10,11,7,2};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                // 比较索引j处的值和j-1处的值，如果索引j-1处的值大，则交换数据，如果是小于，则退出循环
                if(arr[j-1] > arr[j]) {
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                }else { break; }
            }
        }
    }
}
