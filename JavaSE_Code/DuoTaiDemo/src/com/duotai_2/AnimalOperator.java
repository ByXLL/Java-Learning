package com.duotai_2;

/**
 * 动物操作类
 */
public class AnimalOperator {

    // 当存在多个 类 需要创建n个对应的方法去执行 这显然不显示
    // 我们发现 无论是 dog 还是 cat 它们都是继承自 Animal 类
//    public void useAnimal(Dog dog) {
//         // 实际上传的参数就是这个 Dog dog = new Dog();
//        System.out.println("动物的名字是： "+ dog.getName());
//        dog.eat();
//    }
//
//    public void useAnimal(Cat cat) {
//        System.out.println("动物的名字是： "+ cat.getName());
//        cat.eat();
//    }

    // 所以我们只用去封装一个方法 参数就是 它们的父类 Animal
    public void useAnimal(Animal animal) {
        // 传过来的实际上就是  Animal dog = new Dog();
        // 以多态的形式传参  编译看左边 执行看右边
        // Animal 类中没有 getName 方法 编译报错
//        System.out.println("动物的名字是： "+ animal.getName());   //
        // 左边 Animal 类中有 eat 方法  编译通过   执行看右边  右边Dog类中有重写 该方法 所以执行的是Dog类中的方法
        animal.eat();

        // 但是多态不能去访问子类特有的方法  如果需要调用则需要通过转型
//        animal.watchDoor();

    }
}
