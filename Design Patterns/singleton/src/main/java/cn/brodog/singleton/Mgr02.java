package cn.brodog.singleton;

/**
 * 懒汉式  lazy loading
 * 与饿汉式的区别就在于 手动调用 getInstance 后才去实例化，避免了当前类落到内存后就创建了实例
 * 虽然解决了 饿汉式的不能按需加载的问题，但是却会在多线程下存在问题
 * 不推荐使用
 * @author By-Lin
 */
public class Mgr02 {
    private static Mgr02 INSTANCE;

    private Mgr02() {};

    public static Mgr02 getInstance() {
        /**
         *  多线程情况下 第一次进来判断为 null 去 new
         *  如果此时第二个线程也进来了，第一个线程还未完成 new 的操作，则第二个线程也会去执行new
         *  这个时候，两个线程获取到的实例就不是同一个了
         */
        if(INSTANCE == null) { INSTANCE = new Mgr02();}
        return INSTANCE;
    }





    public static void main(String[] args) {
        // 测试多线程  就会出现前后获取的实例不一样问题
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr02.getInstance().hashCode());
            }).start();
        }
    }
}
