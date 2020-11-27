package com.transform_demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CallNameDemo {
    /**
     * 学生点名器
     * @param args
     */
    private static String PATH = "FileOutputStreamDemo\\dist\\files\\array2txt.txt";

    public static void main(String[] args) throws IOException {
        // 创建字符缓冲输入流对象
        BufferedReader br = new BufferedReader(new FileReader(PATH));

        ArrayList<String> studenArray = new ArrayList<String>();

        String line;

        while ((line = br.readLine()) != null) {
            studenArray.add(line);
        }

        br.close();

        // 生成随机数
        Random random = new Random();
        int index = random.nextInt(studenArray.size());

        String student = studenArray.get(index);

        System.out.println("幸运儿是： " + student);

    }
}
