package cn.brodog.reflection2;

import cn.brodog.reflection2.entity.Person;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * 通过反射获取泛型
 * @author By-Lin
 */
public class Main6 {

    public void test01(Map<String, Person> map, List<Person> list) {
        System.out.println("test01");
    }

    public Map<String, Person> test02() {
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = Main6.class.getMethod("test01", Map.class, List.class);

        // 获取泛型参数集合
        Type[] genericExceptionTypes = method.getGenericParameterTypes();
        for (Type type : genericExceptionTypes) {
            System.out.println(type);
            // 如果泛型类型属于一个参数化类型
            if(type instanceof ParameterizedType) {
                // 获取真实的参数类型
                for (Type argument : ((ParameterizedType) type).getActualTypeArguments()) {
                    System.out.println("真实的泛型参数信息-------" + argument);
                }
            }
        }

        System.out.println("-------------------------------");

        // 获取方法的返回值的泛型
        Method method2 = Main6.class.getMethod("test02");
        Type genericReturnType = method2.getGenericReturnType();

        System.out.println(genericReturnType);

        // 如果泛型类型属于一个参数化类型
        if(genericReturnType instanceof ParameterizedType) {
            // 获取真实的参数类型
            for (Type argument : ((ParameterizedType) genericReturnType).getActualTypeArguments()) {
                System.out.println("真实的泛型参数信息-------" + argument);
            }
        }
    }
}
