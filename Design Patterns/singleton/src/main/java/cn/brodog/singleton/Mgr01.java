package cn.brodog.singleton;

/**
 * 饿汉式
 * 类加载到内存后，只实例化一个单例，JVM保证线程安全
 * 推荐使用！
 * 缺点：不管是否使用，类装载时候就完成实例化
 *    例如在使用反射 Class.getName("xxx")的时候当前类落到内存后，就是去初始化 static 修饰的实例，杠精就在纠结，浪费空间啥的
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Mgr01 {
    private static final Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {};

    public static Mgr01 getInstance() { return INSTANCE; }


    public static void main(String[] args) {
        Mgr01 mgr01 = Mgr01.getInstance();
        Mgr01 mgr02 = Mgr01.getInstance();

        // 因为获取的永远都是那个静态实例 为true
        System.out.println(mgr01 == mgr02);     // true
    }
}
