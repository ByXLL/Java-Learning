package com.duotai_2;

import java.util.Objects;

public class TestDemo {
    public static void main(String[] args) {
//        // 创建动物操作类的对象 调用方法
//        AnimalOperator ao = new AnimalOperator();
//
//        // 创建一个 dog 类型的变量
//        Dog dog = new Dog();
//
//        // 然后将 new 出来的 dog 以参数的形式传给 动物操作类
//        ao.useAnimal(dog);
//
//
//        Cat cat = new Cat();
//        ao.useAnimal(cat);

        Integer i = 100;
        Integer i2 = new Integer(100);
        System.out.println(Objects.equals(i,i2));


//        // 解决多态无法调用 子类方法问题
//        Animal dog2 = new Dog();
//        System.out.println(dog2.name);      // 动物
//        dog2.eat();
//
//        dog2.run();
//        // 编译看左边 左边 Animal 类 没有 watchDoor 方法 报错
////        dog2.watchDoor();
//
//        // 使用向下转型法  将父类类型 转成 子类类型 这样就可以使用子类的方法了
//        Dog dog3 = (Dog)dog2;
//        dog3.watchDoor();
    }
}
