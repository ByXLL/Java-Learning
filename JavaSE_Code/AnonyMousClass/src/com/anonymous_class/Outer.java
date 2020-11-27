package com.anonymous_class;

/**
 * 前提：
 *      存在一个类或者接口
 *      这里的类可以是具体类也可以是抽象类
 * 格式：
 *      new 类名或者接口名() {
 *          重写方法
 *      }
 *  本周是什么：
 *      一个继承了该类或者实现了该接口的子类匿名对象
 */
public class Outer {
    public void method() {
        // 这里只是 实现了该类的一个匿名对象
//        new Inter() {
//            @Override
//            public void show() {
//                System.out.println("匿名内部类");
//            }
//        };

        // 调用方法
//        new Inter() {
//            @Override
//            public void show() {
//                System.out.println("匿名内部类");
//            }
//        }.show();

        // 从实现上来说 就是一个inter 接口的实现 其本质就是一个 Inter 类型
        Inter inter = new Inter() {
            @Override
            public void show() {
                System.out.println("匿名内部类");
            }
        };
        // 多次调用 避免需要多次new
        inter.show();
        inter.show();

    }
}
