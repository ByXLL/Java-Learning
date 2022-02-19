package cn.brodog.reflection2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main5 {
    public static void main(String[] args) throws Exception {
        /**
         * 模拟需求：通过读取配置文件中定义的对象和对象的方法，创建该对象并执行方法
         */

        // 1、加载配置文件
        // 1.1 创建 Properties 对象
        Properties properties = new Properties();

        // 1.2 加载配置文件，转换成为一个集合
        /**
         * 1.2.1 获取类路径下的配置文件
         * 使用类加载器的 getResourceAsStream() 去获取类路径下某个文件的文件输入流
         */
        ClassLoader classLoader = Main5.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("pro.properties");
        properties.load(is);

        // 2、获取配置文件中定义的数据
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        // 3、加载该类进内存
        Class aClass = Class.forName(className);

        // 4、创建对象
        Object obj = aClass.newInstance();

        // 5、获取方法
        Method method = aClass.getDeclaredMethod(methodName);
//        method.setAccessible(true);

        // 6、执行方法
        method.invoke(obj);

    }
}
