package com.file_demo;

import java.io.File;

public class DiGuiFilePath {
    /**
     * 递归获取某一个文件夹下面的文件夹及文件
     */
    public static void main(String[] args) {
        // 根据给定的路径创建一个File对象
        File disPath = new File("C:\\By_HJL\\Projects\\Java-Projects\\Java-Learning\\JavaSE_Code");

        //  调用方法
        getAllFileInPath(disPath);
    }

    // 定义一个方法 用于获取目录下的所有内容
    public static void getAllFileInPath(File disPath) {
        // 将目标文件夹下的文件转成数组
        File[] files = disPath.listFiles();

        // 遍历该数组
        if (files != null) {
            for (File item : files) {
                if (item.isDirectory()) {
                    // 当前项是目录  递归 继续循环
                    getAllFileInPath(item);
                }else {
                    // 是文件 直接输出
                    System.out.println(item.getAbsolutePath());
                }
            }
        }else {
            System.out.println("当前文件夹为空");
        }
    }

}
