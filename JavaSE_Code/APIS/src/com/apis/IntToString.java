package com.apis;

/**
 * int 转 string
 */
public class IntToString {
    public static void main(String[] args) {
        int number = 100;
        String s1 = "" + number;
        System.out.println(s1);

        String s2 = String.valueOf(number);
        System.out.println(s2);
    }
}
