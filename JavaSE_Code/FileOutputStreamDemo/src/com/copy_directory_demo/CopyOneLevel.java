package com.copy_directory_demo;

import java.io.*;

public class CopyOneLevel {
    private static String sourcePath = "FileOutputStreamDemo\\dist\\files";
    private static String targetPath = "FileOutputStreamDemo\\dist\\files-bak";

    public static void main(String[] args) throws IOException {
        // 创建数据源目录 File 对象
        File srcFolder = new File(sourcePath);

        // 获取数据源目录File 对象的名称
        String srcFolderName= srcFolder.getName();

        // 创建目的地目录File 对象
        File destFolder = new File(targetPath);

        // 判断目的地文件夹是否存在
        if(!destFolder.exists()) {
            // 创建文件夹
            destFolder.mkdir();
        }

        // 获取源目录下的文件对象
        File[] filesList = srcFolder.listFiles();

        for(File item : filesList) {
            // 获取文件对象的名称
            String srcFileName = item.getName();

            // 创建新的 目的地 File对象
            File destFile = new File(targetPath,srcFileName);

            copyFile(item, destFile);
            System.out.println("文件夹复制成功");
        }
    }

    private static void copyFile(File srcFile, File destFile) throws IOException {
        // 创建一个 字符输入流缓冲对象
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        // 创建一个 字符输入流缓冲对象
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));


        System.out.println(destFile);
        // 创建字节数组
        byte[] bys = new byte[1024];
        int len;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys,0, len);
        }
        bis.close();
        bos.close();
    }
}
