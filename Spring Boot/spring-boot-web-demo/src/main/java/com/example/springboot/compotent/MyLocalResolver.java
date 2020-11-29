package com.example.springboot.compotent;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocalResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 去获取请求里面的 parameter 的关键字
        String lang = request.getParameter("lang");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(lang)) {
            // 字符串截取  重组成地理信息
            String[] split = lang.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
