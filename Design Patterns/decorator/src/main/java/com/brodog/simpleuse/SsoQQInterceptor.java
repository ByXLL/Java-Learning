package com.brodog.simpleuse;

import com.brodog.sso.SsoInterceptor;

/**
 * 简单的处理针对第三方包不完善的情况下的所作处理
 * 通过继承三方包中的类，重写其内部方法，改造添加进我们需要新增的业务代码
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public class SsoQQInterceptor extends SsoInterceptor {
    /**
     * 这样虽然是简单即可实现，但是重写了三方包的方法，如果还需要进行修改，则就需要很多的工作量去修改
     */
    @Override
    public boolean preHandle(String request, String response, Object handler) {
        // todo 修改业务代码，新增我们需要的业务代码
        if(request.startsWith("pass")) { return true; }
        return false;
    }
}
