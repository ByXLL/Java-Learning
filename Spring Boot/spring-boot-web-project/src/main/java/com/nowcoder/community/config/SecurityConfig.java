package com.nowcoder.community.config;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Security  配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements CommunityConstant {
    /**
     * 配置 对静态文件的忽略
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * 授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * antMatchers  设置需要权限校验的 访问地址
         * hasAnyAuthority  声明带有 以下权限才能访问
         * anyRequest().permitAll()  除了上面声明的 其他全部允许
         */
        http.authorizeRequests()
            .antMatchers(
                "/user/setting",
                "/user/upload",
                "/discuss/add",
                "/comment/add/**",
                "/letter/**",
                "/notice/**",
                "/like",
                "/follow",
                "/unfollow"
            ).hasAnyAuthority(
                AUTHORITY_USER,
                AUTHORITY_MODERATOR,
                AUTHORITY_ADMIN
            ).anyRequest().permitAll();
//          关闭csrf
//            .and().csrf().disable();

        /**
         * 权限不够 的时候的处理
         * authenticationEntryPoint  当没有登录的时候的处理
         * accessDeniedHandler      登录了但是权限不足的处理
         *
         */
        http.exceptionHandling()
            .authenticationEntryPoint(new AuthenticationEntryPoint() {
                // 没有登录
                @Override
                public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                    // 通过获取请求头的信息 判断当前的是 ajax还是页面请求
                    String xRequestedWith = request.getHeader("x-requested-with");
                    if ("XMLHttpRequest".equals(xRequestedWith)) {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(String.valueOf(new ApiResult(403,"你还没有登录")));
                    } else {
                        response.sendRedirect(request.getContextPath() + "/login");
                    }
                }
            })
            .accessDeniedHandler(new AccessDeniedHandler() {
                // 权限不足
                @Override
                public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
                    // 通过获取请求头的信息 判断当前的是 ajax还是页面请求
                    String xRequestedWith = request.getHeader("x-requested-with");
                    if ("XMLHttpRequest".equals(xRequestedWith)) {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(String.valueOf(new ApiResult(403,"你没有访问该功能的权限！")));
                    } else {
                        response.sendRedirect(request.getContextPath() + "/denied");
                    }
                }
            });

        /**
         * security 底层会默认拦截 logout 请求 进行退出操作
         * 基于filter，它会在 controller 之前执行，我们在controller 中处理的逻辑将不会被执行
         * 覆盖它的默认逻辑，我们才可以执行自己大的退出代码
         * 通过一个巧妙的方式  修改 logout 的url地址，欺骗security 使得能执行我们自己定义的 /logout
         */
        http.logout().logoutUrl("/xxfafaadasf");
    }
}
