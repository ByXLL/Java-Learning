package cn.brodog.annotation;

/**
 * 注解
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 元注解，meta-annotation 元注解的作用就是负责注解其他注解，
         *
         * @Target  用于描述注解使用范围
         *  TYPE -> 类上
         *  FIELD -> 属性上
         *  METHOD -> 方法上
         *  PARAMETER -> 形参上
         *  CONSTRUCTOR -> 构造器上
         *  LOCAL_VARIABLE -> 局部变量
         *  ANNOTATION_TYPE ->  注解式生命
         *  PACKAGE ->  包上
         *  TYPE_PARAMETER ->  类型参数
         *  TYPE_USE ->  类型使用
         *
         * @Retention   注解在什么状态下有效，用于描述注解的生命周期
         *  SOURCE  ->  源代码阶段
         *  CLASS   ->  .class文件阶段
         *  RUNTIEM ->  运行时阶段 （常用）
         *
         * @Documented  说明该注解将被包含在Javadoc中
         *
         *
         * @Inherited   子类可以继承父类的该注解
         *
         */
    }
}
