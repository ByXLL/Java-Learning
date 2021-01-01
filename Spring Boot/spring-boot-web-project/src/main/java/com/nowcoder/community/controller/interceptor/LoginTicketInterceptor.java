package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CookieUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 登录验证拦截器
 */
@Configuration
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 第一步 从 request 中获取cookie信息 返回对应的cookie信息
        String ticket = CookieUtil.getValue(request,"ticket");
        if(ticket != null) {
            // 第二步 查询凭证 返回当前登录的凭证的信息
           LoginTicket loginTicket =  userService.findLoginTicket(ticket);
           // 第三步 判断返回的ticket 是否存在数据 并且 状态有效 而且 还在有效期内
           if(loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())) {
                // 第四步 使用凭证 去获取用户的信息
               User user = userService.findUserById(loginTicket.getUserId());
               if(user != null) {
                   // 第五步 在本次请求中持有用户  调用我们封装的 HostHolder
                   hostHolder.setUser(user);
               }
           }
        }
        return true;
    }

    // 模板之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user != null && modelAndView != null) {
            // 第六步 向模板视图注入用户信息
            modelAndView.addObject("loginUser",user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 第七步 当模板处理完成 清空数据
        hostHolder.clear();
    }
}
