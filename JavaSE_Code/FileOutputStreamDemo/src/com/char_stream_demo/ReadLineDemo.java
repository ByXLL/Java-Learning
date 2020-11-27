package com.char_stream_demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadLineDemo {
    protected static String oldPath = "FileOutputStreamDemo\\src\\com\\char_stream_demo\\CopyVideoDemo.java";
    public static void main(String[] args) throws IOException {
        /**
         * newLine() 可以在写入字符的时候插入换行符 会自动根据不同的系统
         * readLine() 读取单行文字 如果到达末尾 则返回null
         */
        // 创建一个字符缓冲输入流
        BufferedReader br = new BufferedReader(new FileReader(oldPath));

        /**
         * 单行输出
         */
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
