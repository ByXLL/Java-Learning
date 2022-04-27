package cn.brodog.springprinciple;

import cn.brodog.springprinciple.controller.UserController;
import cn.brodog.springprinciple.service.UserService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过 反射 在controller中注入service
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Test1 {
    public static void main(String[] args) throws Exception {
        UserController userController = new UserController();

        // 获取controller 的Class
        Class userControllerClass = userController.getClass();

        // 创建对象
        UserService userService = new UserService();

        // 获取 controller 中所有的属性
        Field[] declaredFields = userControllerClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String fieldName = declaredField.getName();     // userService
            // 拼接方法的名称
            fieldName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1,fieldName.length());     // userService -> UserService

            // 获取 get/set 方法名
            String getMethodName = "get" + fieldName;
            String setMethodName = "set" + fieldName;

            // 通过方法注入属性的对象
            Method setUserServiceMethod = userControllerClass.getMethod(setMethodName, UserService.class);

            // 反射执行方法 给 UserController 通过 setUserService 方法 注入 userService
            setUserServiceMethod.invoke(userController,userService);

            System.out.println(userController.getUserService());
        }
    }
}
