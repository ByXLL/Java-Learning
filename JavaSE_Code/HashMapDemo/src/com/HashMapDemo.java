package com;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/***
 * 需求 在键盘录入一个字符串 要求控制台输出字符串出现的次数
 */
public class HashMapDemo {
    public static void main(String[] args) {
        // 键盘录入一个字符串
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串");
        String line = sc.nextLine();

        // 创建HashMap 集合， 键是 Character 值是Integer
//        HashMap<Character,Integer> hm = new HashMap<Character, Integer>();

        // 使用TreeMap 可以对键进行排序
        TreeMap<Character,Integer> hm = new TreeMap<Character, Integer>();


        // 遍历字符串，得到每一个字符
        for (int i = 0; i < line.length(); i++) {
            // 获取字符串的每一个字符
            char key = line.charAt(i);

            // 拿到每一个字符作为键 到HashMap集合中寻找对应的值，并返回值
            Integer value = hm.get(key);

            if(value == null) {
                // 如果返回值是null 说明该字符串在HashMap集合中不存在 将该字符作为键 1 (个数) 作为值存储
                hm.put(key,1);
            }else {
                // 如果返回值不是null 说明该字符在HashMap集合中存在，把该值加1， 然后重新存储该字符和对应的值
                value++;
                hm.put(key,value);
            }
        }

        // 获取 hashMap的keys集合
        Set<Character> keySet =  hm.keySet();
        // 遍历HashMap集合，得到键和值
        for (Character key : keySet) {
            // 通过 key 回去 hashMap 对应的值
            Integer value = hm.get(key);
            System.out.println(key + " 出现了  "+ value +"  次");
        }
    }
}
