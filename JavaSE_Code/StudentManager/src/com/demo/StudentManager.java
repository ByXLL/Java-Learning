package com.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    public static void main(String[] args) {
        ArrayList<Student> studentArrayList = new ArrayList<Student>();
        while (true) {
            System.out.println("--------欢迎使用学生管理系统--------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查看单个学生信息");
            System.out.println("5.查看学生列表");
            System.out.println("6.退出");
            System.out.print("请选择的你操作： ");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            if ("1".equals(line)) {
                addStudent(studentArrayList);
            } else if ("2".equals(line)) {
                deleteStudent(studentArrayList);
            } else if ("3".equals(line)) {
                updateStudent(studentArrayList);
            } else if ("4".equals(line)) {
                findStudentByID(studentArrayList);
            } else if ("5".equals(line)) {
                findAllStudent(studentArrayList);
            } else if ("6".equals(line)) {
                System.out.println("感谢使用");
                System.exit(0);
            } else {
                System.out.println("无效操作");
            }
        }
    }

    public static void addStudent(ArrayList<Student> arrayList) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入学生名字：");
        String name = sc.nextLine();
        System.out.print("请输入学生学号：");
        int number = Integer.parseInt(sc.nextLine());
        System.out.print("请输入学生年龄：");
        int age = Integer.parseInt(sc.nextLine());
        Student student = new Student();
        student.setName(name);
        student.setNumber(number);
        student.setAge(age);
        arrayList.add(student);
        System.out.println("学生添加成功");
    }

    public  static void findStudentByID(ArrayList<Student> arrayList) {
        if(arrayList.size() == 0) {
            System.out.println("当前暂无学生信息");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生学号： ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = hasStudent(arrayList,id);

        if(index != -1) {
            Student student = arrayList.get(index);
            System.out.println("姓名\t\t学号\t\t年龄");
            System.out.println(student.getName()+"\t\t"+student.getNumber()+"\t\t"+student.getAge()+"岁");
        }else {
            System.out.println("查询不到该学生信息");
        }
    }

    public static void findAllStudent(ArrayList<Student> arrayList) {
        if(arrayList.size() == 0) {
            System.out.println("当前暂无学生信息");
            return;
        }
        System.out.println("姓名\t\t学号\t\t年龄");
        for (Student student : arrayList) {
            System.out.println(student.getName() + "\t\t" + student.getNumber() + "\t\t" + student.getAge() + "岁");
        }
    }

    public static void deleteStudent(ArrayList<Student> arrayList) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入学生的学号");
        int number = Integer.parseInt(scanner.nextLine());
        int index = hasStudent(arrayList,number);

        if(index != -1) {
            arrayList.remove(index);
        }else {
            System.out.println("没有该学生，请重新输入");
        }

    }

    public static void updateStudent(ArrayList<Student> arrayList) {
        Student newStudent = new Student();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要修改学生的学号: ");
        int oldNumber = Integer.parseInt(scanner.nextLine());

        int index =  hasStudent(arrayList,oldNumber);

        if(index != -1 ){
            System.out.print("请输入学生的姓名: ");
            String name = scanner.nextLine();
            newStudent.setName(name);
            System.out.print("请输入学生的学号: ");
            int number = Integer.parseInt(scanner.nextLine());
            newStudent.setNumber(number);
            System.out.print("请输入学生的年龄: ");
            int age = Integer.parseInt(scanner.nextLine());
            newStudent.setAge(age);

            arrayList.set(index,newStudent);
        }else {
            System.out.println("查询不到该学生信息！");
        }
    }

    public static int hasStudent(ArrayList<Student> arrayList, int number) {
        int index = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            Student student = arrayList.get(i);
            if(student.getNumber() == number) {
                index = i;
                break;
            }
        }
        return index;
    }
}
