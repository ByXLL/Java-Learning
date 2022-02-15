package cn.brodog.cgLibProxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * cgLib 动态代理
 * 实现动态代理不需要接口
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 在cgLib动态代理中，我们不需要去声明代理类需要的接口
         * 那么cglib怎么知道需要生成的方法，其实就是 setSuperclass
         * 生成的是需要代理对象的子类
         * 不同于jdk动态代理使用的asm，想要使用cglib生成动态代理类，不能是 final 修饰的，因为没有asm修改二进制这么强大的功能
         * 但是 cglib 的底层也是使用asm
         */

        // Enhancer 增强器
        Enhancer enhancer = new Enhancer();
        // 设置生成的代理类的 父类
        enhancer.setSuperclass(Maotai.class);
        // 设置生成代理类回调
        enhancer.setCallback(new SellMethodInterceptor());
        // 生成动态代理类
        Maotai maotai = (Maotai) enhancer.create();
        // 调用方法
        maotai.toSell();
    }
}
