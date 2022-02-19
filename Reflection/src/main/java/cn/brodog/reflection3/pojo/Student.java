package cn.brodog.reflection3.pojo;

import cn.brodog.reflection3.annotation.Field;
import cn.brodog.reflection3.annotation.Table;

/**
 * Student
 * @author By-Lin
 */
@Table("table")
public class Student {
    @Field(columnName = "id", type = "long", length = 21)
    private int id;

    @Field(columnName = "name", type = "varchar", length = 10)
    private String name;

    @Field(columnName = "age", type = "int", length = 3)
    private int age;

    public Student() {
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
