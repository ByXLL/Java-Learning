package cn.brodog.simplefactory.factory;

import cn.brodog.simplefactory.product.Car;
import cn.brodog.simplefactory.product.Plane;

/**
 * 简单工厂 交通工具生产工厂
 * 在这里面去声明 有什么交通工具 定义它们的生产过程
 * 在这生成过程中可以定义我们直接的前置操作 权限、、、、、
 * 这种工厂可扩展性不强，一个工厂，什么都生产每添加一个产品，就得去声明一个产品的生产方法
 * 为了解决这种瓶颈，我们就将不同的产品单独建立一个工厂 CarFactory、PlaneFactory
 * @author By-Lin
 */
public class SimpleVehicleFactory {
    /**
     * 创建一台汽车
     * @return      汽车实例
     */
    public Car createCar() {
        // before processing
        System.out.println("制造一台汽车");
        return new Car();
    }

    /**
     * 制造一架飞机
     * @return      飞机实例
     */
    public Plane createPlane() {
        // before processing
        System.out.println("制造一架飞机");
        return new Plane();
    }
}
