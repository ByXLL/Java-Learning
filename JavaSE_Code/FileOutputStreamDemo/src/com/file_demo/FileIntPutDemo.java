package com.file_demo;

import java.io.FileInputStream;
import java.io.IOException;

public class FileIntPutDemo {
    public static void main(String[] args) throws IOException {
        String PATH = "FileOutputStreamDemo\\dist\\files\\long.txt";

        // 创建字节输出流对象
        FileInputStream fis = new FileInputStream(PATH);

        // 创建一个字节数组 长度是每一次往数组中插入的字节的长度 一般是1024的整数倍
        byte[] bys = new byte[1024];

        // read 方法返回的是读取到的字节的长度
        // 最高为字节数组定义的长度 如果没有那么多数据 则返回实际的长度
        // 当没有的时候 返回 -1
//        int len = fis.read(bys);

        int len;
        while ((len = fis.read(bys)) != -1) {
            String text = new String(bys,0,len);
            System.out.print(text);
        }
        fis.close();
    }
}
