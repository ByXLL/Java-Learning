package cn.brodog.reflection2.entity;

/**
 * 实体类
 * @author By-Lin
 */
public class Person {
    public String name;
    private int age;

    public String a;
    protected String b;
    String c;
    private String d;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
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

    public void go() {
        System.out.println("开始走......");
    }

    public void say(String msg) {
        System.out.println("说-------"+msg);
    }

    public Person getMe() {
        return this;
    }

    private void test() {
        System.out.println("测试");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
