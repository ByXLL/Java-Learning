package com.file_demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyTxtDemo {
    public static void main(String[] args) throws IOException {
        String oldPath = "FileOutputStreamDemo\\dist\\files\\long.txt";
        String newPath = "FileOutputStreamDemo\\dist\\filestest.txt";

        // 数据源 输入流对象
        FileInputStream fis = new FileInputStream(oldPath);

        // 创建字节输出流对象
        FileOutputStream fos = new FileOutputStream(newPath);

        int by;
        // 一次读取一个字节 一次写入一个字节
        while ((by= fis.read()) !=- 1) {
            System.out.println("正在复制-----");
            fos.write(by);
        }
        fos.close();
        fis.close();
    }


}
