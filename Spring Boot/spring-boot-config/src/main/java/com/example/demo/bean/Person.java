package com.example.demo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * 使用注解 @ConfigurationProperties  告诉Spring Boot 这里面所有的属性和配置文件中相关的配置进行绑定
 * (prefix = "person") 签注 在配置文件中的名字
 * 只有这个组件是容器中的组件，才能给容器提供 ConfigurationProperties 的功能
 * 所以要在前面 使用 @Component 声明在容器中
 */

@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String name;
    private int age;
    private Boolean isBoss;
    private Map<String, Object> maps;
    private List<Object> list;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isBoss=" + isBoss +
                ", maps=" + maps +
                ", list=" + list +
                ", dog=" + dog +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return isBoss;
    }

    public void setBoss(Boolean boss) {
        isBoss = boss;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
