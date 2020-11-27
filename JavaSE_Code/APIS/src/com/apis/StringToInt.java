package com.apis;

/**
 * String 转 Int
 */
public class StringToInt {
    public static void main(String[] args) {
        // 注意 需要是 双引号包着数字 如果是非数字就会报错
        String s1 = "100";

        // 方式一；String --- Integer ---- int
        Integer i = Integer.valueOf(s1);
        // public int intValue()
        int x = i.intValue();
        System.out.println(x);

        // 方式二 使用 Integer 静态 方法
        int y = Integer.parseInt(s1);
        System.out.println(y);
    }
}
