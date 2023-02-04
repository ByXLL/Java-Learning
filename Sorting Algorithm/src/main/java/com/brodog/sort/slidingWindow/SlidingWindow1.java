package com.brodog.sort.slidingWindow;

import java.util.LinkedList;
import java.util.Objects;

/**
 * 窗口滑动
 * @author By-Lin
 */
public class SlidingWindow1 {
    public static void main(String[] args) {
        /**
         * 场景：一个随机数组，一个固定大小的窗口从左边向右移动，求每一次划过的窗口中的最大值，并返回最大值数组
         * 固定条件：窗口右端和左端只能向右移动，且左端不能超过右端
         * 处理思路：采用一次for循环 + 双端队列实现
         *  双端队列中按值大小进行排序，排在头节点的值保证是当前窗口内最大值，但是在双端队列中，保存的是这个值在源数组中对应的下标，而不是值本身 （可以根据下标获取到对应源数组中的值）
         *  双端队列左右两个都可以弹出数据，当窗口滑动时，数据从队列右端进入，判断当前队列最后一个值是不是比当前值小
         *  如果不是，即比队列最后一个大，则将队列中的最右端的一个值从右边弹出，继续比较，直到队列为空，或者比最后一个小
         *  当比最后一个小，或者队列为空时，往队列中插入当前数组下标
         *  当窗口左端向右边移动时，比较当前队列头节点是不是过期的下标，如果是将头节点从队列左边弹出
         */
        int[] arr = new int[]{10,2,3,10,1,2,4,50,17,10,3,60};
        int[] maxForArr = getMaxForArr(arr, 5);
        for (int i : maxForArr) {
            System.out.print(i + " ");
        }

    }

    /**
     * 获取数组中的 每一次窗口滑动下，窗口内最大值，组成数组返回
     * @param arr       源数组
     * @param w         窗口大小
     * @return          每次窗口内的最大值
     */
    public static int[] getMaxForArr(int[] arr, int w) {
        if(Objects.isNull(arr) || arr.length < w || w < 1) {
            return new int[]{};
        }
        // 返回的数组的初始化    长度 = 源数组长度 - 窗口大小 +
        int[] result = new int[arr.length - w + 1];
        // 双端队列 用于保存符合要求的数组下标
        LinkedList<Integer> queue = new LinkedList<>();
        // 结果数组的初始插入下标
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            /*
             * 当当前双端队列中不为空的时候 并且 当前数组下标大于 或者等于 当前双端队列中的最后一个值 则直接从右边弹出一直值
             * 直到这个双端队列为空，或者当前数 比当前双端队列中的最后一个数据小 才跳出循环
             * 否则一直从右边弹出一个 直到符合
             */
            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()]) {
                queue.pollLast();
            }

            // 当前数 比双端队列中的最后一个数小 可以加入双端队列
            queue.add(i);

            /*
             * 当前双端队列的头一个元素 即当前源数组的下标 等于当前下标 - 窗口大小 = 过期下标
             * 即代表当前双端队列头节点 属于过期下标  即窗口向右滑动了，左边的要出窗口了
             * 此时无论你当前数值是大还是小 都要从左边出队列
             */
            if(queue.peekFirst() == i - w) {
                queue.pollFirst();
            }

            /*
             * 当窗口填满后 才往结果数组中插入数据 为双端队列头节点
             */
            if(!queue.isEmpty() && i >= w -1) {
                result[index++] = arr[queue.peekFirst()];
            }
        }
        return result;
    }

}
