package com.demo;

public class Student {
    private String name;
    private int age;
    private int number;

    public Student() { }
    public Student(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public Student(String name,int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
    public static void showInfo() {
//        System.out.println("姓名："+ this.name +"");
    }
}
