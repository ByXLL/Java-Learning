package cn.brodog.reflection3;

import cn.brodog.reflection3.annotation.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 反射操作注解
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class aClass = Class.forName("cn.brodog.reflection3.pojo.Student");

        // 通过反射获得注解
        Annotation[] classAnnotations = aClass.getAnnotations();
        for (Annotation annotation : classAnnotations) {
            System.out.println(annotation);
        }

        // 获取注解value的值
        Table annotation = (Table) aClass.getAnnotation(Table.class);
        System.out.println(annotation.value());

        // 获得类属性指定的注解
        Field fieldId = aClass.getDeclaredField("id");
        cn.brodog.reflection3.annotation.Field idAnnotation = fieldId.getAnnotation(cn.brodog.reflection3.annotation.Field.class);

        System.out.println(idAnnotation.columnName());
        System.out.println(idAnnotation.type());
        System.out.println(idAnnotation.length());
    }
}
