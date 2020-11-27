package com.collection_demo;

import java.util.*;

/**
 * Collection 集合存储学生对象并遍历
 */
public class CollectionStudentDemo {
    public static void main(String[] args) {
        // 创建 Collection 对象
        Collection<Student> studentList = new ArrayList<Student>();

        // 创建学生对象
        Student student1 = new Student("张三1",1);
        Student student2 = new Student("张三2",2);
        Student student3 = new Student("张三3",3);
        Student student4 = new Student("张三4",4);

        // 将学生添加到集合
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        // 遍历集合
        Iterator<Student> it = studentList.iterator();
        while (it.hasNext()) {
            Student student = it.next();
            System.out.println("学生姓名： "+student.getName()+ "   学号： "+ student.getId());
        }

        // 注意在使用 Iterator 去做遍历的时候不能去修改 list
        // 因为内部 会去记录你的 预期遍历次数和实际遍历次数  当你调用 add 方法后 会去修改实际遍历此时
        // 当内部判断这两个值不同的时候就会去报错 并发修改异常
        // 推荐使用 for 或者使用 ListIterator 列表迭代器去遍历



        List<String> studentList2 = new ArrayList<String>();

        studentList2.add("student1");
        studentList2.add("student2");
        studentList2.add("student3");
        studentList2.add("student4");

        ListIterator<String> lit = studentList2.listIterator();
        while (lit.hasNext()) {
            String s = lit.next();
            if(s.equals("student4")) {
                // 注意 这里使用的是 迭代器的 add方法 不是使用 list的add方法
                // 使用 list的add还是会报错 并发修改异常
                lit.add("student5");
            }
        }
        System.out.println(studentList2);
    }
}
