package com.myclass;

public class GirlFriend {
    // pricate 权限修饰符

    private int height,age,weight;
    private String name;



    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // 构造方法     格式 public className() {}
    // 当在new 对象的时候 就会去调用到构造方法  如果我们没有写 构造方法 系统也会默认有一个无参构造方法

    /**
    *
    * @param 默认构造方法
    */
    public GirlFriend() {
    System.out.println("你去new了一个女朋友,啥也没有");
    }


    /**
     *
     * @param 构造方法传入名字
     */
    public GirlFriend(String name) {
        this.name = name;
        System.out.println("你去new了一个女朋友,名字叫 " + this.name);
    }

    /**
     *
     * @param 构造方法传入年龄
     */
    public GirlFriend(int age) {
        this.age = age;
        System.out.println("你去new了一个女朋友,年龄 " + this.age);
    }

    /**
     *
     * @param 构造方法传入完全信息
     */
    public GirlFriend(String name, int age,  int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        System.out.println("你去new了一个完美的女朋友");
    }



    public void sayILoveyou() {
        System.out.println("我爱你");
    }
}
