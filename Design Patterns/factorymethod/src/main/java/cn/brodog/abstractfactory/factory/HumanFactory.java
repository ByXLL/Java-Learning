package cn.brodog.abstractfactory.factory;

import cn.brodog.abstractfactory.food.Food;
import cn.brodog.abstractfactory.food.Rice;
import cn.brodog.abstractfactory.move.Car;
import cn.brodog.abstractfactory.move.Run;
import cn.brodog.abstractfactory.weapon.AK47;
import cn.brodog.abstractfactory.weapon.Weapon;

/**
 * 人类 生产工厂 继承至抽象工厂
 * @author By-Lin
 */
public class HumanFactory extends AbstractFactory{
    /**
     * 抽象方法 定义如何去移动
     *
     * @return run 具体实现类
     */
    @Override
    public Run toRun() {
        return new Car();
    }

    /**
     * 抽象方法 定义怎么吃
     *
     * @return food 具体实现类
     */
    @Override
    public Food toEat() {
        return new Rice();
    }

    /**
     * 抽象方法 定义如何攻击
     *
     * @return Weapon 具体实现类
     */
    @Override
    public Weapon toAttack() {
        return new AK47();
    }
}
