package com.brodog.juc.threadsafety;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发 线程不安全演示
 * @author By-Lin
 */
@SuppressWarnings("all")
public class ThreadNoSafety {
    public static void main(String[] args) {
        // 并发修改集合

//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<String>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        // 模拟多线程修改和查询集合
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                // 修改
                list.add(UUID.randomUUID().toString().substring(0,5));
                // 查询
                System.out.println(list);
            }).start();
        }
    }
}
