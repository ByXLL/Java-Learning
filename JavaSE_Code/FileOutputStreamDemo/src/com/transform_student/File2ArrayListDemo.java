package com.transform_student;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File2ArrayListDemo {
    /**
     * txt 转 学生对象数组
     * @param args
     */

    private static String PATH = "FileOutputStreamDemo\\dist\\files\\students.text";
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH));

        ArrayList<Student> students = new ArrayList<Student>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] strArray = line.split(",");
            Student student = new Student();
            student.setId(Integer.parseInt(strArray[0]));
            student.setName(strArray[1]);
            student.setClas(strArray[2]);
            students.add(student);
        }
        bufferedReader.close();

        for (Student student: students) {
            System.out.println(student.getId() + ","+ student.getName() + "," + student.getClas());
        }
    }
}
