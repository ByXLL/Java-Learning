package cn.brodog.abstractfactory.food;

/**
 * 青草类 继承至食物抽象类 并重写抽象类方法 对当前类做具体描述
 * @author By-Lin
 */
public class Grass extends Food {
    /**
     * 抽象方法 获取食物名称
     */
    @Override
    public void getFoodName() {
        System.out.println("吃的是青草......");
    }
}
