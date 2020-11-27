package com.file_demo;

import java.io.*;

public class CopyVideoDemo {
    protected static String videoPath = "FileOutputStreamDemo\\dist\\files\\video.mp4";
    protected static String targetPath = "FileOutputStreamDemo\\dist\\video\\video-bak.mp4";
    public static void main(String[] args) throws IOException {
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        // 复制视频
        method4();
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时间： "+ (endTime - startTime) + "毫秒");
    }

    // 基本字节流 一次读一个字节
    public static void method1() throws IOException {
        // 先获取输入 字节流
        FileInputStream fis = new FileInputStream(videoPath);

        // 获取 输出字节流  目的地
        FileOutputStream fos = new FileOutputStream(targetPath);
        System.out.println("视频复制中...");

        int by;
        while ((by= fis.read()) != -1) {
            fos.write(by);
        }
        fis.close();
        fos.close();
    }

    // 基本字节流 一次读一个字节数组
    public static void method2() throws IOException {
        // 先获取输入 字节流
        FileInputStream fis = new FileInputStream(videoPath);

        // 获取 输出字节流  目的地
        FileOutputStream fos = new FileOutputStream(targetPath);
        System.out.println("视频复制中...");

        // 字节数组
        byte[] bytes = new byte[1024];
        int len;
        while ((len= fis.read(bytes)) != -1) {
            // 根据字节数组偏移量写入
            fos.write(bytes,0,len);
        }
        fis.close();
        fos.close();
    }

    // 字节缓存流 一次读一个字节
    public static void method3() throws IOException {
        // 先获取输入 字节流
        BufferedInputStream bfis = new BufferedInputStream(new FileInputStream(videoPath));

        // 获取 输出字节流  目的地
        BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(targetPath));
        System.out.println("视频复制中...");

        // 字节数组
        int by;
        while ((by= bfis.read()) != -1) {
            // 根据字节数组偏移量写入
            bfos.write(by);
        }
        bfis.close();
        bfos.close();
    }

    // 字节缓存流 一次读一个字节数组
    public static void method4() throws IOException {
        // 先获取输入 字节流
        BufferedInputStream bfis = new BufferedInputStream(new FileInputStream(videoPath));

        // 获取 输出字节流  目的地
        BufferedOutputStream bfos = new BufferedOutputStream(new FileOutputStream(targetPath));
        System.out.println("视频复制中...");

        // 字节数组
        byte[] bytes = new byte[1024];
        int len;
        while ((len= bfis.read(bytes)) != -1) {
            // 根据字节数组偏移量写入
            bfos.write(bytes,0,len);
        }
        bfis.close();
        bfos.close();
    }
}
