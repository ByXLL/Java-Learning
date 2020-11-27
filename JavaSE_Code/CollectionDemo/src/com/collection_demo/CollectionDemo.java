package com.collection_demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/***
 * Collection :
 * 创建Collection集合的对象需要以多态的形式创建 使用具体的ArrayList的实现类
 */
public class CollectionDemo {
    public static void main(String[] args) {
        // 创建 Collection 集合的对象
        Collection<String> cList = new ArrayList<String>();
        // 向集合中添加元素 调用add方法 默认返回true  可以添加相同的元素
        cList.add("张三");
        cList.add("张三");
        cList.add("李四");
        System.out.println(cList);
//        // 其他方法 remove(Object o) clear()  contains(Object o) isEmpty() size()
//        cList.remove("张三");
//        System.out.println(cList);
//        // 是否包含某个 元素
//        System.out.println(cList.contains("李四"));

        // 迭代器
        // 通过 Iterator<E> iterator()  返回此集合中元素的迭代器
        Iterator<String> it = cList.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
