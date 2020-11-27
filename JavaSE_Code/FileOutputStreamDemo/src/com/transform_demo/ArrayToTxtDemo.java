package com.transform_demo;

import java.io.*;
import java.util.ArrayList;

public class ArrayToTxtDemo {
    /**
     * 把ArrayList 集合中的字符串数据写入到文本文件
     *
     * @param args
     */
    private static String PATH = "FileOutputStreamDemo\\dist\\files\\array2txt.txt";

    public static void main(String[] args) throws IOException {
        // 创建ArrayList集合
        ArrayList<String> strArray = new ArrayList<String>();
        // 往集合中存储字符串元素
        for (int i = 0; i < 100; i++) {
            strArray.add("张三-" + i);
        }
        // 创建字符缓冲输出流对象
        BufferedWriter bos = new BufferedWriter(new FileWriter(PATH));

        // 遍历集合 拿到每一个字符串数据
        for (String item : strArray) {
            // 调用字符缓冲输出流对象进行写入数据
            bos.write(item);
            bos.newLine();
            bos.flush();
        }

        // 释放资源
        bos.close();
    }
}
