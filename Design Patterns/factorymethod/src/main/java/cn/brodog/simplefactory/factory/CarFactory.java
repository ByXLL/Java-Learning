package cn.brodog.simplefactory.factory;

import cn.brodog.simplefactory.product.Car;
import cn.brodog.simplefactory.product.Moveable;

/**
 * 汽车 生产工厂
 * @author By-Lin
 */
public class CarFactory {
    public Moveable create() {
        // before processing
        System.out.println("制造一台汽车");
        return new Car();
    }
}
