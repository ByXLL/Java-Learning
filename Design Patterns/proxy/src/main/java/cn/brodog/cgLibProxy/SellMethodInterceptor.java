package cn.brodog.cgLibProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 我们自定义的方法拦截器 必须实现 MethodInterceptor接口
 * 等同与 jdk 动态代理中的 InvocationHandler
 * @author By-Lin
 */
@SuppressWarnings("all")
public class SellMethodInterceptor implements MethodInterceptor {
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        /**
         * Object o 这个时候就是 enhancer.setSuperclass(Maotai.class) 生成的子类
         */
        System.out.println("生成动态代理的对象的父类-----" + o.getClass().getSuperclass().getName());

        System.out.println("method " + method.getName() + "   start....");
        Object result = methodProxy.invokeSuper(o,objects);
        System.out.println("method " + method.getName() + "   end....");
        return result;
    }
}
