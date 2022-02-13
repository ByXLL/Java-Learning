package cn.brodog.flyweight;

/**
 * 享元模式 flyweight 重复利用对象
 * 共享元数据 最简单的体现就是 将多个整合到一个大的里面
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 享元模式 一个深刻的体现就是 池化
         * 当我们需要获取一个新的对象的时候，我们不去 new 而是，从一个大的对象里面拿
         * 就是一个类的内部，提前new出来了一堆，需要用的话就直接获取一个，如果提前声明的用完了，才去new，
         * 使用的时候，修改当前这个的状态，当使用完成了，再将状态修改成未使用，以此来回反复
         * 就类似于 人民币，当我们要用钱的时候，我们不需要直接去印刷，而是使用已经发行流通的
         */


        /**
         * String 类就是一个享受元模式
         * 直接用 双引号包起来的，这个时候，就会将字符串存放到常量池中  s1 == s2
         * 而使用 new String 创建的，这个时候，在内存中就是不同的两个内存地址  s3 != s4
         * 而如果装的是一个常量的话，它依旧会去常量池里面找，没有才会在常量池中创建
         * .intern() 就是获取指向常量池的引用，故 s3.intern() == s1
         */

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");

        System.out.println(s1 == s2);       // true
        System.out.println(s1 == s3);       // false
        System.out.println(s3 == s4);       // false
        System.out.println(s3.intern() == s1);       // true
        System.out.println(s3.intern() == s4.intern());       // true


    }
}
