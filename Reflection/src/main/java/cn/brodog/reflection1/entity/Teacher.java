package cn.brodog.reflection1.entity;

/**
 * 老师类
 * @author By-Lin
 */
public class Teacher extends Person {
    public String specialty;
    private String role;


    public Teacher() {
        this.name = "xxx老师";
        this.role = "老师";
    }

    public Teacher(String specialty, String role) {
        this.specialty = specialty;
        this.role = role;
    }

    public Teacher(String name, int age, String specialty, String role) {
        super(name, age);
        this.specialty = specialty;
        this.role = role;
    }

    private void teach() {
        System.out.println("老师教----"+specialty);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", specialty='" + specialty + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
