package com.brodog.juc.volatiledemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * volatile 有序性
 * @author By-Lin
 */
@SuppressWarnings("all")
public class VolatileSerialDemo {
    private static volatile int x = 0, y = 0;

    public static void main(String[] args) throws Exception {
        Set<String> resSet = new HashSet<>();
        Map<String,Integer> resultMap = new HashMap<>();
        for (int i = 0; i < 1000000; i++) {
            x = 0; y = 0;
            resultMap.clear();
            Thread t1 = new Thread(()->{
                int a = y;
                x = 1;
                resultMap.put("a",a);
            });

            Thread t2 = new Thread(()->{
                int b = x;
                y = 1;
                resultMap.put("b",b);
            });

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            resSet.add("a=" + resultMap.get("a") + "," + "b=" + resultMap.get("b"));
            System.out.println(resSet);
        }
    }
}
