package com.treeset_demo;

import java.util.TreeSet;

/**
 * TreeSet 集合点
 *  1.元素有序，这里的顺序不是指存储和取出的顺序，而是按照一定的规则进行配许，具体的排序方式取决于构造方法
 *      TreeSet() 根据其元素的自然排序进行排序
 *      TreeSet(Comparator comparator) 根据指定的比较器进行排序
 *  2.没有带索引的集合，所以不能 使用普通的for循环遍历
 *  3. 由于是set集合， 所以不包含重复元素的集合
 *
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        // 创建集合对象
        TreeSet<Integer> ts = new TreeSet<Integer>();
        // 添加元素

        ts.add(100);
        ts.add(200);
        ts.add(300);
        ts.add(400);

        ts.add(100);
        // 遍历集合
        for (Integer i : ts) {
            // 不包含重复元素
            // 自然排序输出
            System.out.println(i);
        }
    }
}
