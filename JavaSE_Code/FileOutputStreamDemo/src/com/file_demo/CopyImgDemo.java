package com.file_demo;

import java.io.*;

public class CopyImgDemo {
    public static void main(String[] args) throws IOException {
        String imgPath = "FileOutputStreamDemo\\dist\\imgs\\timg.jpg";
        String imgCopyPath = "FileOutputStreamDemo\\dist\\imgs\\dog\\";
        // 创建输入流对象
        FileInputStream fis = new FileInputStream(imgPath);

        new File(imgCopyPath).mkdirs();
        // 创建输出流对象
        FileOutputStream fos = new FileOutputStream(imgCopyPath + "dog.jpg");

        // 读写数据 复制图片  一次一个字节
        byte[] bys = new byte[1024];
        int len;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys,0,len);
        }
        System.out.println("图片复制成功");
        fis.close();
        fos.close();
    }
}
