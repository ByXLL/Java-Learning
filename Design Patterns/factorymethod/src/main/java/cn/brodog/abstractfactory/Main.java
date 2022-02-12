package cn.brodog.abstractfactory;

import cn.brodog.abstractfactory.factory.AbstractFactory;
import cn.brodog.abstractfactory.factory.HumanFactory;
import cn.brodog.abstractfactory.food.Food;
import cn.brodog.abstractfactory.food.Rice;
import cn.brodog.abstractfactory.move.Car;
import cn.brodog.abstractfactory.move.Run;
import cn.brodog.abstractfactory.weapon.AK47;
import cn.brodog.abstractfactory.weapon.Weapon;

/**
 * 抽象工厂
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 抽象工厂 目前有这样的需求
         * 一个人和一个动物 都能吃、能工具、能移动
         * 通过创建对应的抽象工厂 人类工厂、动物工厂
         * 创建出具体的人，人能使用人类的武器来攻击，吃人类的米饭、使用人类的车来移动
         * 创建出具体的动物，动物只能通过嘴来攻击，吃动物的饲料，使用脚来走路移动
         */

        // 在没有使用工厂模式前 我们完成上面操作就需要去new出对应的类并执行对应的类上的方法
//        Run car = new Car();
//        car.go();
//        .......

        /**
         * 使用抽象工厂后 我们先通过人类工厂创建一个人类
         * 然后再调用人类的某个技能（抽象工厂中定义的方法）并执行该技能（改方法的具体实现的方法）
         */
        AbstractFactory human = new HumanFactory();
        Run humanRun = human.toRun();
        humanRun.go();
        Weapon humanWeapon = human.toAttack();
        humanWeapon.attack();
        Food humanFood = human.toEat();
        humanFood.getFoodName();
    }
}
