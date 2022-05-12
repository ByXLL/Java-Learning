package com.brodog.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author By-Lin
 */
public class QuickSort {
    public static void main(String[] args) {
        Integer[] arr = {10,3,4,10,11,7,2};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * 交换位置
     */
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    /**
     * 比较元素大小
     */
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    /**
     * 对数组中的元素进行排序
     * @param arr
     */
    private static void sort(Comparable[] arr) {
        int l = 0;
        int h = arr.length -1;
        sort(arr,l,h);
    }

    /**
     * 对数组中从索引 l 到索引 h 之间的元素进行排序
     * @param arr
     * @param l
     * @param h
     */
    private static void sort(Comparable[] arr, int l, int h) {
        if(h<=l) { return; }
        // 需要对数组中 l 索引到 h 索引的元素进行分组，左子组和右子组
        int partition = partition(arr, l, h);

        // 让左子组有序
        sort(arr, l, partition -1);

        // 让右子组有序
        sort(arr, partition +1, h);

    }

    // 对数组a中﹐从索引Lo到索引 hi之间的元素进行分组﹐并返回分组界限对应的索引
    public static int partition(Comparable[] arr, int l, int h) {
        // 确定分界值
        Comparable key = arr[l];

        // 定义两个指针，分别指向待切分元素的最小索引处和最大索引处的下一个位置
        int left = l;
        int right = h + 1;

        // 切分
        while (true) {
            // 先从右往左扫描，移动right指针，找到一个比分界值元素小的，停止
            while (less(key,arr[--right])) {
                if(right == l) { break;}
            }

            // 再从左往右扫描，移动left指针，找到一个比分界值大的元素，停止
            while (less(arr[++left],key)) {
                if(left == h) { break; }
            }

            // 判断 left >= right 如果是，则证明元素扫描完毕，结束
            if(left >= right) { break; }
            else {
                exch(arr, left, right);
            }
        }
        // 交换分界值
        exch(arr, l, right);
        return right;
    }
}