package com.file_demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutPutDemo {
    public static void main(String[] args){
        FileOutputStream fos = null;
        String PATH = "FileOutputStreamDemo\\dist\\files";

        File file = new File(PATH);

        // 其实在 调用 FileOutputStream 的时候内部是 做了一个new File(path) 的操作
        System.out.println("创建文件夹 " + file.mkdirs());
        try{
            // 创建文件夹和文件
            file.mkdir();

            // 第二个 参数为是否为追加
            fos = new FileOutputStream(PATH+"test.txt",true);

            /**
             * getBytes 返回字符串对应的字节数组
             * write(字节||字节数组, 开始下标 int, 输入个数 int， )
             */
            for (int i = 0; i < 10; i++) {
                fos.write("hello".getBytes());
                fos.write("\n".getBytes());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
