package com.char_stream_demo;

import java.io.*;

public class CopyJavaFileDemo {
    protected static String oldPath = "FileOutputStreamDemo\\src\\com\\char_stream_demo\\CopyJavaFileDemo.java";
    protected static String targetPath = "FileOutputStreamDemo\\dist\\java\\CopyJavaFileDemo-bak.java";

    public static void main(String[] args) throws IOException {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 复制文件
        method3();
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时间： "+ (endTime - startTime) + "毫秒");
    }

    // 基本字符流 一次读一个字符数组
    public static void method1() throws IOException {
        // 使用 FileReader 可以省去 new InputStreamReader 直接输入路径
        // 根据数据源创建字符输入流对象
        FileReader frs = new FileReader(oldPath);

        // 获取 输出字符流  目的地
        FileWriter fws = new FileWriter(targetPath);
        System.out.println("文件复制中...");

        // 字符数组
        char[] chars = new char[1024];
        int len;
        while ((len= frs.read(chars)) != -1) {
            // 根据字节数组偏移量写入
            fws.write(chars,0,len);
        }
        frs.close();
        fws.close();
    }

    // 字符缓存流 一次读一个字符数组
    public static void method2() throws IOException {
        // 根据数据源创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader(oldPath));

        // 根据目的地创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath));

        System.out.println("文件复制中...");

//        // 一次一个字符数据
//        int ch;
//        while ((ch= br.read()) != -1) {
//            // 根据字节数组偏移量写入
//            bw.write(ch);
//        }

        // 一次一个字符组数组
        char[] chs = new char[1024];
        int len;
        while ((len= br.read(chs)) != -1) {
            // 根据字节数组偏移量写入
            bw.write(chs,0,len);
        }

        br.close();
        bw.close();
    }

    // 字符缓存流 一次读一行
    public static void method3() throws IOException {
        // 根据数据源创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader(oldPath));

        // 根据目的地创建字符缓冲输出流对象
        BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath));

        System.out.println("文件复制中...");

        // 一次一行
        String line;
        while ((line= br.readLine()) != null) {
            // 写入一行
            bw.write(line);
            // 写的时候换行
            bw.newLine();
            // 写完就刷新流
            bw.flush();
        }

        br.close();
        bw.close();
    }
}
