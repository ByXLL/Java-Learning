package cn.brodog.abstractfactory.move;

/**
 * 汽车类
 * @author By-Lin
 */
public class Car extends Run{
    @Override
    public void go() {
        System.out.println("开车移动......");
    }
}
