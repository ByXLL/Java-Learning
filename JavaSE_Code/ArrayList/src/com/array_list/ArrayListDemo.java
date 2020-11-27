package com.array_list;

import java.util.ArrayList;

public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<String>();
        // 添加元素
        arrayList.add("我是你爸爸");
        arrayList.add("我是你爹");
        arrayList.add("你是煞笔");
        System.out.println(arrayList);
        System.out.println(arrayList.size());

        // 删除元素
        arrayList.remove("你是煞笔");   // 返回 true false
        arrayList.remove(0);        // 不能超长度
        System.out.println(arrayList);
        System.out.println(arrayList.size());

        System.out.println(arrayList.get(0));
        // set (index, data)

    }
}
