package com.brodog;

import com.brodog.entity.Person;
import com.brodog.entity.Student;

/**
 * 测试Class类的创建方式
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Test02 {
    public static void main(String[] args) throws Exception {
        Person person = new Student();
        System.out.println("这个人是-----"+person.name);

        // 方式一：通过对象获取
        Class c1 = person.getClass();
        System.out.println(c1.hashCode());


        // 方式二：通过Class.forname()获得
        Class c2 = Class.forName("com.brodog.entity.Student");
        System.out.println(c2.hashCode());


        // 方式三：通过类名.class获得
        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        /**
         * 基本内置类型的包装类都有一个Type属性
         */
//        Class c4 = Integer.TYPE;
//        System.out.println(c4.hashCode());      // int


         
    }
}
