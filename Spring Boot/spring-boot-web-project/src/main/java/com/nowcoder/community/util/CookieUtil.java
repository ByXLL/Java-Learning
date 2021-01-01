package com.nowcoder.community.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 封装的 cookie 工具类
 */
public class CookieUtil {
    public static String getValue(HttpServletRequest request, String key) {
        if(request == null || key == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                // 判断传进来的 key 是不是存在
                if(cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
