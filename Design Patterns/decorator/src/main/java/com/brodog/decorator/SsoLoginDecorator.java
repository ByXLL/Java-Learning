package com.brodog.decorator;
import com.brodog.sso.HandlerInterceptor;


/**
 * 单点登录
 * 在当前类中，通过继承自我们抽象封装的 SsoInterceptor 这个上层类，而不是直接就是三方包中的那个类，在重写的方法中去添加我们需要添加的装饰
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public class SsoLoginDecorator extends SsoDecorator {
    public SsoLoginDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        /**
         * 这一步调用了父类的方法 就是可以不用管其他的事情，如果三方库改了，我们也不用动我们的代码
         * 这样就实现了对原本或者说上层逻辑代码不做修改，只是在它处理完成后，我们再套上我们自己需要添加的东西
         * 就类似与毛坯房的柱子和墙壁都有了，我们不去动它原来的东西，在外面贴上瓷砖，达到装饰的效果
         * 如果还有人在我们的基础上再做装饰，就可以以此类推，可以做到无限套娃
         */
        boolean success = super.preHandle(request, response, handler);
        // todo 添加我们自己需要的装饰
        if(request.startsWith("pass") && success) {
            return true;
        }
        return false;
    }
}
