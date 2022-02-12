package cn.brodog.abstractfactory.factory;

import cn.brodog.abstractfactory.food.Food;
import cn.brodog.abstractfactory.move.Run;
import cn.brodog.abstractfactory.weapon.Weapon;

/**
 * 抽象工厂
 * 这里用来定义 工厂的默认生产方式
 * 定义抽象方法 用于创建 攻击、移动、吃饭这几个共有的行为
 * 然后创建具体的人类工厂、动物工厂继承至当前的抽象工厂
 * 再从这两个工厂创建出人和动物
 * @author By-Lin
 */
public abstract class AbstractFactory {
    /**
     * 抽象方法 定义如何去移动
     * @return      run 具体实现类
     */
    public abstract Run toRun();

    /**
     * 抽象方法 定义怎么吃
     * @return  food 具体实现类
     */
    public abstract Food toEat();

    /**
     * 抽象方法 定义如何攻击
     * @return  Weapon 具体实现类
     */
    public abstract Weapon toAttack();
}
