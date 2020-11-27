package com.chouxiang;

public abstract class Animal {
    private String name;
    private int age = 10;
    private final String cate = "动物";


    public Animal() {
    }

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 当一个类 中存在抽象方法 或者 方法没有方法体 则这个类就需要声明为抽象类
    public abstract void eat();

    public void sleep() {
        System.out.println(getName() + " 喜欢 睡觉");
    }

    public void show() {
        System.out.println(getName() +" 是一个"+ cate+ "  今年 "+age+" 岁了");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCate() {
        return cate;
    }
}
