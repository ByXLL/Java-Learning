package com.extend_demo;

public class Animal {
    private String name;
    private int age;

    // 无参构造方法
    public Animal() {}

    // 带参构造方法
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
