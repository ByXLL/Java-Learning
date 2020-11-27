package com.interface_demo2;

public class TestDemo {
    public static void main(String[] args) {
        // 实现接口
        Jumpping dog1 = new Dog();
        dog1.jump();
        System.out.println("---------------");


        Animal dog2 = new Dog();
        dog2.setName("小金毛");
        dog2.setAge(3);
        dog2.eat();
        ((Dog) dog2).jump();
        System.out.println("---------------");

        Animal dog3 = new Dog("柴犬",1);
        dog3.eat();
        ((Dog) dog3).jump();

        // 多态调用 向下 子类方法
        Dog dog4 = (Dog)dog3;
        dog4.showInfo();

        System.out.println("---------------");
        Animal cat = new Cat("蓝猫",1);
        cat.eat();
        ((Cat) cat).jump();
        ((Cat) cat).showInfo();

    }
}
