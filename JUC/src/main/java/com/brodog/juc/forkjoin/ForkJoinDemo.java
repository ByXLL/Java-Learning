package com.brodog.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并 fork/join
 * 实现 从 1 加到 100  并且两数的差值不超过10  二分算法
 *
 *                   1 ~ 12
 *          1 ~ 25   13 ~ 25
 * 1 ~ 50
 *          26 ~ 50
 * @author By-Lin
 */
public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建拆分对象 MyTask
        MyTask myTask = new MyTask(1,100);

        // 创建分支合并池对象
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        // 获取最终合并的结果
        Integer result = forkJoinTask.get();
        System.out.println(result);

        // 关闭池对象
        forkJoinPool.shutdown();
    }
}

class MyTask extends RecursiveTask<Integer> {

    // 声明插值
    private final static Integer value = 10;

    // 开始值
    private int begin;
    // 结束值
    private int end;
    // 结果
    private int result;

    // 创建一个有参构造
    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    // 处理拆分和合并的逻辑
    @Override
    protected Integer compute() {
        // 判断相加的值是否大于10
        if((end - begin) <= value) {
            // 相加
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }else {
            // 进一步拆分
            // 获取中间值
            int middle = (begin + end) / 2;
            // 拆分左边
            MyTask myTask1 = new MyTask(begin,middle);
            // 拆分右边
            MyTask myTask2 = new MyTask(middle+1,end);

            // 执行拆分
            myTask1.fork();
            myTask2.fork();

            // 合并两个子任务的结果
            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
}