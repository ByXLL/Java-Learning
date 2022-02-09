package cn.brodog.singleton;

/**
 * 静态内部类方式
 * JVM保证了单例，因为jvm加载一个类，保证了只加载一次
 * 加载外部类的时候，不会去加载当前类的内部类，这样也解决静态类初始化问题
 * 完美解决方案
 * @author By-Lin
 */
public class Mgr05 {
    private Mgr05() {};

    /**
     * 创建静态内部类
     */
    private static class Mgr05Holder {
        private final static Mgr05 INSTANCE = new Mgr05();
    }

    public static Mgr05 getInstance() {
       return Mgr05Holder.INSTANCE;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr05.getInstance().hashCode());
            }).start();
        }
    }
}
