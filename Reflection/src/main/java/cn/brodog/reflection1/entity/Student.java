package cn.brodog.reflection1.entity;

/**
 * 老师类
 * @author By-Lin
 */
public class Student extends Person {
    private String clas;

    public Student() {
        this.name = "学生";
    }

    public Student(String clas) {
        this.clas = clas;
    }

    public Student(String name, int age, String clas) {
        super(name, age);
        this.clas = clas;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", clas='" + clas + '\'' +
                '}';
    }
}
