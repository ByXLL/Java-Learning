package cn.brodog.reflection2;

import cn.brodog.reflection2.entity.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Class 对象的使用
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main4 {
    public static void main(String[] args) throws Exception {
        /**
         * Class 对象功能
         *  3、获取成员方法们
         *      * 执行方法  .invoke(object)
         *      * 获取方法名     .get
         *  、、、、、、
         */
        Class personClass = Person.class;
        Person person = new Person();

        // 操作成员方法

        /**
         * 获取方法，可以根据参数，重载
         */
        // 获取空参方法
        Method personMethodGo = personClass.getDeclaredMethod("go");
        // 执行空参方法
        personMethodGo.invoke(person);

        // 获取带参方法, (@NonNls @NotNull String name Class<?>... parameterTypes)，可变参数的 类型.class 使用逗号追加
        Method personMethodSay = personClass.getDeclaredMethod("say", String.class);
        // 执行带参方法
        personMethodSay.invoke(person,"你好");

        // 执行方法，并获取返回值
        Method personMethodGetMe = personClass.getDeclaredMethod("getMe");
        Object invoke = personMethodGetMe.invoke(person);
        System.out.println(invoke);

        System.out.println("-------------------------------------------");

        /**
         * 获取方法集合
         * getDeclaredMethods 获取的是当前类自己定义
         */
        Method[] methods = personClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
            String methodName = method.getName();
            System.out.println(methodName);
        }

        System.out.println("-------------------------------------------");


        // 获取类名
        String className = personClass.getName();
        System.out.println(className);
    }
}
