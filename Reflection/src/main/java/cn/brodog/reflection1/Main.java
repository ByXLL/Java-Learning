package cn.brodog.reflection1;

/**
 * 获取各种类加载器
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // 获取系统类加载器 / 应用加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        // 获取系统类加载器的父类 -> 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        // 获取扩展类加载器 / 根加载器 （C/C++编写） 我们获取不到 null
        ClassLoader rootClassLoader = extClassLoader.getParent();
        System.out.println(rootClassLoader);

        // 获取某个类是什么加载器加载的
        ClassLoader classLoader = Class.forName("cn.brodog.reflection1.Main").getClassLoader();
        System.out.println(classLoader);

        // 核心类库加载的
        classLoader = Class.forName("java.lang.Object").getClassLoader();
        System.out.println(classLoader);

        // 获取系统类加载器可以加载的路径
        System.out.println(System.getProperty("java.class.path"));

    }
}
