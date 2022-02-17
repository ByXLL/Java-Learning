package cn.brodog.reflection2;

import cn.brodog.reflection2.entity.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Class 对象的使用
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main3 {
    public static void main(String[] args) throws Exception {
        /**
         * Class 对象功能
         *  2、获取构造方法
         *      * 创建对象  .newInstance()
         */
        Class personClass = Person.class;
        Person person = new Person();

        /**
         * 获取空参构造方法  getDeclaredConstructor()
         */
        Constructor declaredConstructor1 = personClass.getDeclaredConstructor();
        // 忽略修饰符权限
//        declaredConstructor1.setAccessible(true);

        System.out.println(declaredConstructor1);
        System.out.println("-------------------------------------------");

        /**
         * 使用空参构造方法 创建对象实例
         * 如果使用空参构造方法，可以使用 Class对象的.newInstance() 进行简化
         */
        Object newPerson1 = declaredConstructor1.newInstance();
        System.out.println(newPerson1);
        System.out.println("-------------------------------------------");
        // 等同于 personClass.newInstance();


        /**
         * 获取带参构造方法  getDeclaredConstructor(参数.class)
         */
        Constructor declaredConstructor2 = personClass.getDeclaredConstructor(String.class, int.class);
        System.out.println(declaredConstructor2);
        System.out.println("-------------------------------------------");

        /**
         * 使用带参构造方法 创建对象实例
         */
        Object newPerson2 = declaredConstructor2.newInstance("张三", 18);
        System.out.println(newPerson2);
        System.out.println("-------------------------------------------");

        // 获取所有的构造方法
        Constructor[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
    }
}
