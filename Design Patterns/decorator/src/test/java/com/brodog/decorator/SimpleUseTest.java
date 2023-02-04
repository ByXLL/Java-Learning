package com.brodog.decorator;

import com.brodog.simpleuse.SsoQQInterceptor;
import org.junit.Test;

/**
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public class SimpleUseTest {

    @Test
    public void simpleUseTest() {
        SsoQQInterceptor qqInterceptor = new SsoQQInterceptor();
        boolean preHandle = qqInterceptor.preHandle("pass-qq-123456", "qqqqq", "");
        System.out.println("QQ-handle: " + preHandle);
    }
}
