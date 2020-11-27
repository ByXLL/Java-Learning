package com.interface_demo;

public interface Jumpping {
    // 接口里面的成员变量 默认是被 final 修饰的常量 不能再修改  而且还是被 static 修饰的 可以通过 Jumpping.name 获取

    public static final int number = 0;
    // 所以 在接口中声明的成员变量 都是常量需要 赋默认值 可以省去修饰符
    String name = "名字";


    // 接口里面是没有构造方法的
    // 在接口实现 中声明的构造方法 其实是继承 Object 根

    // 接口里面只能有 抽象方法 不能有非抽象方法  定义方法 可以省去修饰符
//    public abstract void jump();
    void jump();
}
