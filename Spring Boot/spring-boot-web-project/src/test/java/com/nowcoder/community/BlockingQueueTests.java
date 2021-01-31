package com.nowcoder.community;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列 测试类
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class BlockingQueueTests {
    public static void main(String[] args) {
        // 实例化 阻塞队列 最多10个任务
        BlockingQueue queue = new ArrayBlockingQueue(10);
        // 创建一个生产者 n个消费者
        new Thread(new Producer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();

    }
}

/**
 * 生产者
 */
class Producer implements Runnable {

    // 阻塞队列
    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try{
            // 一直生产
            for (int i = 0; i < 100; i++) {
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + "生产：" + queue.size());
            }
        }catch (Exception e) {

        }
    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    // 阻塞队列
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(new Random().nextInt(1000));
                queue.take();
                System.out.println(Thread.currentThread().getName() + "消费：" + queue.size());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
