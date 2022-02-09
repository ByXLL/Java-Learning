package cn.brodog.singleton;

/**
 * 懒汉式  lazy loading
 * 与饿汉式的区别就在于 手动调用 getInstance 后才去实例化，避免了当前类落到内存后就创建了实例
 * 虽然解决了 饿汉式的不能按需加载的问题，但是却会在多线程下存在问题
 * 不推荐使用
 * 解决方案 给方法加同步锁
 * 但是性能会降低
 * @author By-Lin
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {};

    public static synchronized Mgr03 getInstance() {
        if(INSTANCE == null) { INSTANCE = new Mgr03();}
        return INSTANCE;
    }





    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }
}
