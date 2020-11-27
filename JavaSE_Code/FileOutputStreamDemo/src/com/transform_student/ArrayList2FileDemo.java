package com.transform_student;

import com.transform_student.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayList2FileDemo {
    /**
     * 学生对象输出到txt
     * @param args
     */

    private static String PATH = "FileOutputStreamDemo\\dist\\files\\students.txt";
    public static void main(String[] args) throws IOException {

        ArrayList<Student> students =  new ArrayList<Student>();

        // 生成随机数  (最小值+Math.random()*(最大值-最小值+1))
        Student student;
        for (int i = 0; i < 10; i++) {
            int id = (int) (10+Math.random()*(100-10+1));
            String name = "张三"+ i;
            String clas = (int) (1+Math.random()*(6)) +"年级-" + (int) (1+Math.random()*(10)) + "班";
            student = new Student(name,id,clas);
            students.add(student);
        }

        // 创建字符输出流
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH));
        for (Student item : students) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(item.getId()).append(",").append(item.getName()).append(",").append(item.getClas());
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
        bufferedWriter.close();

    }
}
