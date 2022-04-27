package com.brodog.aop.interceptor;


import com.brodog.aop.annotation.PassTokenRequired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * token 权限拦截器
 * @author By-mali
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
//    private final UserService userService;
//
//    public AuthenticationInterceptor(UserService userService) {
//        this.userService = userService;
//    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前请求的控制器方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 检查是否有 PassTokenRequired 注释，有则跳过认证
        if (method.isAnnotationPresent(PassTokenRequired.class)) {
            PassTokenRequired passToken = method.getAnnotation(PassTokenRequired.class);
            if(passToken.required()) { return true; }
            else { return false; }
        }else {
            return false;
        }

//        // 从请求中获取 token 字段
//        String token = request.getHeader("token");
//        // 如果不是映射到方法直接通过
//        if(!(handler instanceof HandlerMethod)){ return true; }
//
//        // 获取当前请求的控制器方法
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        String requestPath = request.getRequestURI();
//
//        //检查是否有 PassTokenRequired 注释，有则跳过认证
//        if (method.isAnnotationPresent(PassTokenRequired.class)) {
//            PassTokenRequired passToken = method.getAnnotation(PassTokenRequired.class);
//            if(passToken.required()) { return true; }
//        }else {
//            // 执行认证
//            if (token == null) { throw new LoginException("无token，请重新登录"); }
//            // 获取 token 中的 user id
////            String userId;
//            Map<String, Object> userInfo;
//            Integer role;
//            try {
////                userId = JWT.decode(token).getAudience().get(0);
//                userInfo = JWT.decode(token).getClaims().get("userInfo").asMap();
//                role = (Integer) userInfo.get("role");
//            } catch (JWTDecodeException j) {
//                j.printStackTrace();
//                throw new LoginException("401,权限异常");
//            }
////            User user = userService.getById(Integer.parseInt(userId));
////            if (user == null) {
////                throw new LoginException("用户不存在，请重新登录");
////            }
//            // 验证 token
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(CommUtils.SECRET)).build();
//            try {
//                jwtVerifier.verify(token);
//            } catch (JWTVerificationException e) {
//                e.printStackTrace();
//                throw new LoginException("401,权限异常");
//            }
//            boolean isAdminPath = requestPath.contains("/admin/");
//            boolean isAppPath = requestPath.contains("/app/");
//            if(role == 1 && isAdminPath) { return true; }
//            if(role == 2 && isAppPath) { return true; }
//            throw new LoginException("401,权限异常");
//        }
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
