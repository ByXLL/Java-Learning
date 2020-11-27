package com.array_list;

import java.util.ArrayList;

public class StudentList {
    // 需求 录入学生信息
    public static void main(String[] args) {
        // 创建学生对象集合
        ArrayList<Student> arrayList = new ArrayList<Student>();

        // 创建学生对象
        for (int i = 0; i < 10; i++) {
            Student student = new Student("姓名"+(i+1),18+i);
            arrayList.add(student);
        }
        System.out.println(arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            Student student = arrayList.get(i);
            System.out.println(student.getName()+"  "+ student.getAge());
        }
    }
}
