package cn.brodog.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 反射生产代理必须面向接口，这是由Proxy的内部实现决定的
 * 动态代理
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 在静态代理中，我们通过 实现Sell 接口，能创建 Maotai 的代理
         * 这个时候是我们知道 Se ll 接口里面的 sell() 方法，
         * 如果我们要同时做别的代理，代理汽车销售，或者其他的，这个时候我们就不能只是实现Sell接口了
         * 因为不同的代理，我们不知道它有什么方法，这个时候就需要动态代理了
         * 动态代理，就是代理的实现类不是我们自己手写的，(MaotaiGxProxy 类)
         */
        final Maotai maotai = new Maotai();

        // 将动态代理生产的类保存下来
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles","true");

        Sell sell = (Sell) Proxy.newProxyInstance(
                // 需要代理的对象的 loader
                Maotai.class.getClassLoader(),
                // new 出来的这个代理对象 （MaotaiGxProxy）要实现的接口的数组  这必须要有接口方法
                new Class[]{Sell.class},
                // 调用处理器 声明调用被代理对象的方法的时候的操作 Sell.toSell()
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("method " + method.getName() + "   start....");
                        // 相当于调用了 MaotaiGxNnProxy.toSell();
                        // 最根本的执行底层是 asm asm能直接操作内存二进制
                        Object o = method.invoke(maotai,args);
                        System.out.println("method " + method.getName() + "   end....");
                        return o;
                    }
                }
        );

        sell.toSell();
    }
}
