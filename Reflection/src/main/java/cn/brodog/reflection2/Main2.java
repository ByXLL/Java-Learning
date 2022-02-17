package cn.brodog.reflection2;

import cn.brodog.reflection2.entity.Person;

import java.lang.reflect.Field;

/**
 * Class 对象的使用
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main2 {
    public static void main(String[] args) throws Exception {
        /**
         * Class 对象功能
         *  1、获取成员变量们
         *      * 获取值
         *      * 设置值
         *          当忽略访问修饰符的权限时，需要给 field 设置 .setAccessible(true)
         *  2、获取构造方法们
         *  3、获取成员方法们
         *  4、获取类名
         *  、、、、、、
         */
        Class personClass = Person.class;
        Person person = new Person();

        // 操作成员变量

        // 获取指定名称的 public 修饰的成员变量
        Field fieldName = personClass.getField("name");
        System.out.println(fieldName);

        System.out.println("-------------------------------------------");

        /**
         * 获取成员变量 name 的值
         * Field 对象中的get(Object obj)方法      参数obj是具体的实例对象
         */
        Object o = fieldName.get(person);
        System.out.println(o);      // 默认 String 类型初始化值 null

        /**
         * 设置成员变量 name 的值
         * Field 对象中的set(Object obj,Object value)方法      参数obj是具体的实例对象  value是具体的值
         */
        fieldName.set(person,"张三");
        System.out.println(person);

        System.out.println("-------------------------------------------");

        /**
         * 设置私有成员变量 age的值
         * 在反射中，无论是什么修饰符，都能修改和获取
         * 但是需要配置 忽略访问修饰符的安全检查，否则会报错
         */
        Field fieldAge = personClass.getDeclaredField("age");
        // 忽略访问修饰符
        fieldAge.setAccessible(true);
        Object pa = fieldAge.get(person);
        System.out.println(pa);
        fieldAge.set(person,18);
        System.out.println(person);
        System.out.println("-------------------------------------------");

        // 获取 public 修饰的成员变量们
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("-------------------------------------------");

        // 获取所有的成员变量们
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }

    }
}
