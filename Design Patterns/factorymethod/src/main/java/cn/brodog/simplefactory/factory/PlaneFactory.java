package cn.brodog.simplefactory.factory;

import cn.brodog.simplefactory.product.Moveable;
import cn.brodog.simplefactory.product.Plane;

/**
 * 飞机生产工厂
 * @author By-Lin
 */
public class PlaneFactory {
    public Moveable create() {
        // before processing
        System.out.println("制造一架飞机");
        return new Plane();
    }
}
