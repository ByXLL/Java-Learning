package cn.brodog.simplefactory;

import cn.brodog.simplefactory.factory.SimpleVehicleFactory;
import cn.brodog.simplefactory.product.Moveable;

/**
 * 任何一种可以产生对象的方法或者类，都可以称之为工厂
 * 单例也是一种工厂
 * 为什么有new后，还要有工厂
 *      1、可以灵活控制生产过程
 *      2、可以添加权限、修饰、日志、、、、、
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        // 模拟一个场景 选择驾驶的交通工具  可能是自行车、汽车、飞机

        // 选择开汽车 这个时候直接new一个car
//        Car car = new Car();
//        car.go();


        /**
         * 如果是多个交通工具，就得 new 多个精确的交通工具类型 Car、Plane
         * 这个时候我们去将类型改为 Moveable 接口的实现类，在每一个具体的交通类型中去实现 Moveable 接口的方法
         * 本质上就是多态的应用 与上面的区别不大
         */
//        Moveable car1 = new Car();
//        car1.go();
//        Moveable plane1 = new Plane();
//        plane1.go();


        // 使用简单工厂创建 一台汽车
        Moveable car2 = new SimpleVehicleFactory().createCar();
        car2.go();
    }
}
