package com.treeset_demo;

import java.util.TreeSet;

public class TreeSetStudentDemo {
    public static void main(String[] args) {
        // 创建学生对象集合
        TreeSet<Student> ts = new TreeSet<Student>();

        Student s1 = new Student("张三",200);
        Student s2 = new Student("李四",210);
        Student s3 = new Student("王五",120);
        Student s4 = new Student("王五",120);
        ts.add(s1);
        ts.add(s2);
        ts.add(s3);
        ts.add(s4);

        for (Student s : ts) {
            System.out.println(s.getName() + "   " + s.getId());
        }
    }
}
