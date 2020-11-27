package com.copy_directory_demo;

import java.io.*;

public class CopyMoreLevel {

    private static String sourcePath = "FileOutputStreamDemo\\dist";
    private static String targetPath = "FileOutputStreamDemo\\dist-bak";

    public static void main(String[] args) throws IOException {
        // 创建数据源 File 对象
        File srcFile = new File(sourcePath);
        // 创建目的地 File 对象
        File destFile = new File(targetPath);

        if(!destFile.exists()) {
            destFile.mkdirs();
        }
        // 记录开始时间
        long startTime = System.currentTimeMillis();

        copyFolder(srcFile,destFile);

        // 记录结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时间： "+ (endTime - startTime) + "毫秒");

        System.out.println("文件复制成功");
    }

    /***
     * 复制文件夹方法
     * @param srcFile 源路径
     * @param destFile  新路径
     * @throws IOException
     */
    public static void copyFolder(File srcFile, File destFile) throws IOException {
        // 获取当前 源路径 的文件对象的名字  （文件夹名||文件名）
        String srcFileName = srcFile.getName();
        if(srcFile.isDirectory()) {
            // 在目的地创建相同的文件夹
            File newFolder = new File(destFile,srcFileName);  // 新创建文件对象
            System.out.println(newFolder);
            // 如果该文件夹不存在 去创建
            if(!newFolder.exists()) {
                newFolder.mkdir();
            }

            // 获取数据源 File 下所有文件或者目录的File数组
            File[] fileList = srcFile.listFiles();

            // 遍历该File数组 获取每一个对象
            for (File item : fileList) {
                // 递归 继续获取文件夹
                copyFolder(item,newFolder);
            }
        }else {
            // 该文件对象是文件
            // 去创建 需要复制的File对象
            File newFile = new File(destFile,srcFile.getName());
            // 调用复制文件方法 将新的目标文件对象进去回去;
            copyFile(srcFile,newFile);
        }
    }

    /***
     * 复制文件方法
     * @param srcFile 源路径
     * @param destFile  新路径
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        // 创建字节缓冲输入流对象
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFile));
        // 创建字节输出流对象
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(destFile));

        byte[] bytes = new byte[1024];
        int len;

        while ((len = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes,0,len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
