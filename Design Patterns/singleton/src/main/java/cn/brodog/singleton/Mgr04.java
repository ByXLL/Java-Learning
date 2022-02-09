package cn.brodog.singleton;

/**
 * 懒汉式  lazy loading
 * 懒汉式 最终写法
 * @author By-Lin
 */
public class Mgr04 {
    // 需要加上 volatile 防止编译成c 后的指令重排问题  在没有初始化的时候 就直接返回 INSTANCE
    private static volatile Mgr04 INSTANCE;

    private Mgr04() {};

    public static  Mgr04 getInstance() {
        if(INSTANCE == null) {
            // 解决 synchronized 带来的性能问题 就将 synchronized 放到方法内部
            // 但是这样等于没有锁 在多线程下是有问题的
//            synchronized(Mgr04.class) {
//                INSTANCE = new Mgr04();
//            }

            // 使用双重检查来处理这个问题
            synchronized(Mgr04.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Mgr04();
                }
            }
        }
        return INSTANCE;
    }





    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
