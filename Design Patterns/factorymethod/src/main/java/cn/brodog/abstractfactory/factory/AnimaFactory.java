package cn.brodog.abstractfactory.factory;

import cn.brodog.abstractfactory.food.Food;
import cn.brodog.abstractfactory.food.Grass;
import cn.brodog.abstractfactory.move.Run;
import cn.brodog.abstractfactory.move.Walk;
import cn.brodog.abstractfactory.weapon.Mouth;
import cn.brodog.abstractfactory.weapon.Weapon;

/**
 * 动物工厂 继承至 抽象工厂
 * @author By-Lin
 */
public class AnimaFactory extends AbstractFactory{
    /**
     * 抽象方法 定义如何去移动
     *
     * @return run 具体实现类
     */
    @Override
    public Run toRun() {
        return new Walk();
    }

    /**
     * 抽象方法 定义怎么吃
     *
     * @return food 具体实现类
     */
    @Override
    public Food toEat() {
        return new Grass();
    }

    /**
     * 抽象方法 定义如何攻击
     *
     * @return Weapon 具体实现类
     */
    @Override
    public Weapon toAttack() {
        return new Mouth();
    }
}
