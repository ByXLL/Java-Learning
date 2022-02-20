package cn.brodog.springprinciple;

import cn.brodog.springprinciple.controller.AdminController;
import cn.brodog.springprinciple.controller.UserController;
import cn.brodog.springprinciple.service.UserService;

import java.util.stream.Stream;

/**
 * 通过注解方式在controller中注入service
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Test2 {
    public static void main(String[] args) {
        AdminController adminController = new AdminController();
        Class userControllerClass = adminController.getClass();

        UserService userService = new UserService();

        // 获取所有的属性值
        Stream.of(userControllerClass.getDeclaredFields()).forEach(field -> {
            // 获取当前属性的名称
            String fieldName = field.getName();

            // 获取当前属性的注解
            AutoWired annotation = field.getAnnotation(AutoWired.class);
            if(annotation != null) {
                field.setAccessible(true);

                // 获取属性类型
                Class fieldType = field.getType();
                // 通过 new 一个属性的实例
                try {
                    Object serviceInstance = fieldType.newInstance();
                    // 给属性 设置值 等同于 private UserService userService = new UserService()
                    field.set(adminController,serviceInstance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(adminController.getUserService());
    }
}
