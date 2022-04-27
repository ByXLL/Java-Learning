package com.byxll.util;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author By-Lin
 */
@SuppressWarnings("all")
public class StringUtil {

    // 先拿到注入的属性
    @Autowired
    private UtilProperties properties;

    public String getNewString() {
        return "这是新的---" + properties.getSourceString();
    }
}
