package com.duotai;

public class Demo {
    public static void main(String[] args) {
        // 多态的形式 创建一个 由父类引用指向子类对象 的对象
        Animal dog =  new Dog();

        // 虽然我们在内存中看到的是dog 但是在外部看到的是 Animal
        // 所以以多态的形式访问成员变量的时候 访问的是父类中的  编译要看左边 运行也要看左边
        System.out.println(dog.age);    // 多态访问成员变量  编译看左边 运行也是看左边 所以输出的是父类的成员变量
//        System.out.println(dog.weight);     // 父类没有该成员变量 编译报错

        dog.eat();      // 多态访问成员方法 编译看左边  运行看右边  因为在子类中有重写  所以调用的是 子类中的方法
//        dog.wangWang();  // 父类没有该成员方法 编译报错


    }
}
