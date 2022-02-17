package cn.brodog.reflection2;

import cn.brodog.reflection2.entity.Person;

/**
 * 类的运行流程与，与获取Class对象的方法
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * 什么是反射：反射就是将类的各个组成部分封装成为其他对象，这就是反射机制
         * 好处：
         *      1、可以在程序运行过程中，操作这些对象
         *      2、可以解耦，提高程序的扩展性
         */

        /**
         * 在Java中一个类需要运行，经历了三个阶段
         * 阶段一：源代码阶段，我编写的.java文件，声明类的格式，定义属性，方法，使用javac编译，编译成 .class 字节码文件，字节码文件中又包含，成员变量、构造方法、成员方法、、、、、
         * 阶段二：Class类对象阶段，使用类加载器 ClassLoader，将字节码文件加载到内存中去，在内存中会使用一个 Class 去描述这个字节码文件
         *        这就是Java中的 Class 类，在Class中存在三个特别重要的部分
         *          1、成员变量  将字节码文件中的成员变量封装成 Filed 对象。 Filed[] fileds
         *          2、构造方法  将字节码文件中的构造方法量封装成 Constructor 对象。 Constructor[]  constructors
         *          3、成员方法  将字节码文件中的成员方法封装成 Method 对象。  Method[] methods
         * 阶段三：运行时阶段，new 对象
         */


        /**
         * 获取Class对象的方式：
         *      1、在源代码阶段使用，Class.forName("cn.brodog.Student")，将字节码文件加载进内存，返回Class文件
         *          * 多用于在配置文件中，程序通过读取配置文件来加载类
         *      2、Class类对象阶段使用，类名.class (Student.class)
         *          * 多用于方法参数之间的传递
         *      3、运行时阶段，对象.getClass()， .getClass() 方法，继承自Object类中
         *          这个时候类已经转换成为了一个具体的实例， Student student = new Student()   student.getClass()
         *          * 多用于已经有实例对象了
         */

        // 方式一：字节码文字对象
        Class cls1 = Class.forName("cn.brodog.reflection2.entity.Person");
        System.out.println(cls1);

        // 方式二：类名.class 方式
        Class cls2 = Person.class;
        System.out.println(cls2);

        // 方式三：对象.getClass
        Person person = new Person();
        Class cls3 = person.getClass();
        System.out.println(cls3);

        System.out.println(cls1 == cls2);       // true
        System.out.println(cls1 == cls3);       // true

        /**
         * 结论：
         *      同一个字节码文件（.class文件）在一次程序运行过程中，只会被加载一次，无论通过这三种方式中的任何一种，它获取到的都是加载进内存中的同一个 Class
         */

    }
}
