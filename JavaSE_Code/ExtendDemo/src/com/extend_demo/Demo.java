package com.extend_demo;

public class Demo {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setName("柴犬");
        dog.setAge(3);
        System.out.println(dog.getName() + dog.getAge() +"岁");
        dog.wangWang();

        Cat cat = new Cat("蓝猫",3);
        System.out.println(cat.getName() + cat.getAge() +"岁");
        cat.catchMouse();
    }
}
