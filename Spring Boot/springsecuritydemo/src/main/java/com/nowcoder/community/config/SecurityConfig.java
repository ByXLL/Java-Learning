package com.nowcoder.community.config;


import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * security 配置类
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * 忽略 静态文件夹 下的请求拦截
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * 去对权限去做认证
     * AuthenticationManager 认证的核心接口
     * AuthenticationManagerBuilder 用于构建AuthenticationManager的对象的工具
     * ProviderManager  AuthenticationManager接口的默认实现类
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  需要将我们 定义的service传入 底层通过 service中 实现的 loadUserByUsername 方法 查出user

        // 内置的认证规则
//        // passwordEncoder 对密码进行编码  对登录时候的密码 进行加盐 加密
//        auth.userDetailsService(userService).passwordEncoder(new Pbkdf2PasswordEncoder("123456"));

        // 自定义认证规则
        // AuthenticationManager 持有一组 AuthenticationProvider  每一个 AuthenticationProvider 负责一种认证
        // AuthenticationManager 不亲自去做认证 是通过 委托模式 委托给 包含的一个或者 AuthenticationProvider 去做认证
        auth.authenticationProvider(new AuthenticationProvider() {
            /**
             * Authentication 用于封装 认证信息(账号、密码)的接口
             * 不同的实现类代表不同类型的认证信息
             * @param authentication        账号密码认证
             * @return                      认证结果    类型却决于 supports 中申明的 类型
             * @throws AuthenticationException
             */
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                String userName = authentication.getName();
                String passWord = (String) authentication.getCredentials();
                // 通过 拿security拦截的登录回传的  用户名 去查询数据库中的用户信息
                User user = userService.findUserByName(userName);
                if(user == null) {
                    throw new UsernameNotFoundException("账号不存在");
                }
                passWord = CommunityUtil.md5(passWord + user.getSalt());
                if(!user.getPassword().equals(passWord)) {
                    throw new BadCredentialsException("密码错误");
                }
                /**
                 * 参数： 主要信息（用户对象）、证书（密码）、权限
                 */
                return new UsernamePasswordAuthenticationToken(user,user.getPassword(),user.getAuthorities());
            }

            /**
             * 返回 AuthenticationProvider 这个接口 支持的哪一种认证类型
             * 返回 使用账号密码的认证模式
             * @param authentication
             * @return
             */
            @Override
            public boolean supports(Class<?> authentication) {
                // UsernamePasswordAuthenticationToken 就是 Authentication 接口常用的 用户名密码 校验的实现类
                return UsernamePasswordAuthenticationToken.class.equals(authentication);
            }
        });

    }

    /**
     * 重写 security 默认配置的 configure
     * 在父类中的这个方法 	执行了	http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * 代表了 使用了security 任何网络请求都会去 校验登录 并跳转到 默认的登录页
     * 在这里 我们重写 父类的 configure 使它跳转到我们自己的登录页
     * 在这个处理完后 才会走到 configure(WebSecurity web) 方法 去校验用户信息
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登录相关的配置  设置登录页的访问路径 和 登录表单提交的路径 用于拦截提交信息
        // 面对简单的回调处理  可以通过  .successForwardUrl().failureForwardUrl() 设置 校验成功和校验失败的跳转路径
        // 在复杂的回调处理中 可以通过 successHandler 和 failureHandler 设置更多自定义配置
        http.formLogin().loginPage("/loginpage").loginProcessingUrl("/login")
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    // 登陆成功 重定向跳转首页
                    response.sendRedirect(request.getContextPath() + "/index");
                }
            })
            .failureHandler(new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                    // 登录失败 回传 登录失败信息 并返回登录页 (不能使用重定向，会发起新的请求，会丢失携带的参数)
                    // 通过将返回信息 携带回去 并通过转发的形式 将当前的 request 和 response 转发过去
                    request.setAttribute("error",exception.getMessage());
                    request.getRequestDispatcher("/login").forward(request,response);
                }
            });

        // 退出的相关配置
        http.logout().logoutUrl("/logout").logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.sendRedirect( request.getContextPath() + "/index");
            }
        });

        /**
         * 授权配置
         * antMatchers 设置 需要对应权限的访问路径 可多个
         * hasAnyAuthority  可访问的对应的角色名
         * .and().exceptionHandling() 当权限匹配失败  4
         */
        http.authorizeRequests()
            .antMatchers("/letter").hasAnyAuthority("USER","ADMIN")
            .antMatchers("/admin").hasAnyAuthority("ADMIN")
            .and().exceptionHandling().accessDeniedPage("/denied");

        /**
         * 增加 filter 校验验证码
         * 参数： 定义的过滤器   在那个拦截器之前
         */
        http.addFilterBefore(new Filter() {
            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
                // 强转一下 父类
                HttpServletRequest request = (HttpServletRequest) servletRequest;
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                // 判断访问的路径是 login
                if(request.getServletPath().equals("/login")) {
                    // 获取验证码
                    String verifyCode = request.getParameter("verifyCode");
                    if(verifyCode == null || !verifyCode.equalsIgnoreCase("1234")) {
                        request.setAttribute("error","验证码错误");
                        request.getRequestDispatcher("/loginpage").forward(request,response);
                        return;
                    }
                }
                // 让请求继续往下走，走到下一个filter
                chain.doFilter(request, response);
            }
        }, UsernamePasswordAuthenticationFilter.class);

        /**
         * 记住我
         * tokenRepository 设定 保存的方案
         * InMemoryTokenRepositoryImpl  代表记录到内存中
         * tokenValiditySeconds  过期时间
         * userDetailsService  设置对应的service 当未过期的时候 再次打开浏览器 会从service 获取用户名 然后调内存中的校验信息 跳过验证
         */
        http.rememberMe().tokenRepository(new InMemoryTokenRepositoryImpl())
            .tokenValiditySeconds(3600*24)
            .userDetailsService(userService);
    }
}
