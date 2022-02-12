package cn.brodog.abstractfactory.move;

/**
 * 移动
 * @author By-Lin
 */
public class Walk extends Run {
    @Override
    public void go() {
        System.out.println("用脚走路......");
    }
}
