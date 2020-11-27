package com.treeset_demo;


// 这里需要去 实现 Comparable 接口 并重写compareTo方法
public class Student implements Comparable<Student> {
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Student o) {
        // 需要修改返回饿值 0 代表重复 不插入 1 正序 -1 反序
        // 按照学号排序 这里会又一个默认 this  将和上一个 对象的id对比 通过对比值 来决定返回的
        int num = this.id - o.id;

        // 但是只通过 id 判断不现实 还需要去比对别的东西
        int num2 =  num==0?this.name.compareTo(o.name) : num;
        return num2;
    }
}
