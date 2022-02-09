package cn.brodog.singleton;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    /**
     * 单例模式：
     * 保证一个类，只能存在一个实例
     * 在一个类内部 将他的构造函数进行私有化  再去定义一个私有的静态常量 就是当前类的实例
     * 设置一个公共静态的方法，用于获取当前类内部的私有实例
     * 代码：
     *      private static final Mgr01 INSTANCE = new Mgr01();
     *      private Mgr01() {};
     *      public static Mgr01 getInstance() { return INSTANCE; }
     */
}
