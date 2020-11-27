package com.chouxiang;

public class AnimalDemo {
    public static void main(String[] args) {
//        Animal animal = new Animal();       // 报错 Animal是一个抽象类 不是具体的 所以不能创建对象

        // 如果需要实例化 则需要参照多态的方式  创建一个子类
        Animal animal = new Dog();
        animal.eat();
        animal.sleep();
        animal.show();

        Animal dog = new Dog();
        dog.setName("小金毛");
        dog.setAge(3);
        dog.show();
        dog.eat();

        Animal dog2 = new Dog("柴犬",1);
        dog2.show();
        dog2.eat();
        dog2.sleep();

        Animal cat = new Cat("美短",1);
        cat.show();
        cat.sleep();
        cat.eat();
    }
}
