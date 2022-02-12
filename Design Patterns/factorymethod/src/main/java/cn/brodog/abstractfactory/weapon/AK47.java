package cn.brodog.abstractfactory.weapon;

/**
 * AK47 Weapon武器继承的子类
 * 重写攻击方法
 * @author By-Lin
 */
public class AK47 extends Weapon {
    /**
     * 武器的攻击
     */
    @Override
    public void attack() {
        System.out.println("使用AK47攻击......");
    }
}
