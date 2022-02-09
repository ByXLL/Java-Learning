package cn.brodog.singleton;

/**
 * 枚举类
 * 不仅解决了线程问题，还防止了反序列化
 * 因为枚举类没有构造方法，通过反射的时候，无法创建实例
 * 哪怕拿到了 INSTANCE ，最后获取 INSTANCE; 拿到的还是单例创建的同一个对象
 * 完美中的完美
 * @author By-Lin
 */
public enum Mgr06 {
    INSTANCE;


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(Mgr06.INSTANCE.hashCode());
            }).start();
        }
    }
}
