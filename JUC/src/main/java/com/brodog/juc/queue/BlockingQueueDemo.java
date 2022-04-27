package com.brodog.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列
 * @author By-Lin
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个阻塞队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 第一组 使用 add remove element
//        System.out.println(queue.add("1"));   // true
//        System.out.println(queue.add("2"));
//        System.out.println(queue.add("3"));
////        System.out.println(queue.add("1"));  // 抛出异常
//
//        System.out.println(queue.element());    // 第一个元素
//
//        System.out.println(queue.remove());   // 移除的元素
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());

        // 第二组 使用 offer poll peek
//        System.out.println(queue.offer("1"));   // true
//        System.out.println(queue.offer("2"));
//        System.out.println(queue.offer("3"));
//        System.out.println(queue.offer("4"));   // false
//
//        System.out.println(queue.poll());   // 移除的元素
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());   // null

        // 第三组 使用 put take
//        queue.put("1");
//        queue.put("2");
//        queue.put("3");
////        queue.put("1");     // 队列满了 一直阻塞
//
//        System.out.println(queue.take());   // 移除的元素
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println(queue.take());    // 队列为空 一直阻塞

        // 第四组 使用 offer poll
        System.out.println(queue.offer("1", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("2", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("3", 1, TimeUnit.SECONDS));
        System.out.println(queue.offer("4", 1, TimeUnit.SECONDS));  // false

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

    }
}
