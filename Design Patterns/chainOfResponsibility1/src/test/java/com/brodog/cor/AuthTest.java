package com.brodog.cor;

import org.junit.Test;

/**
 * @author By-BroDog
 * @createTime 2023-01-25
 */
public class AuthTest {

    @Test
    public void testAuth() {
        AuthLink authLink = new Level1AuthLink(1, "张三")
                .appendNext(new Level2AuthLink(2, "李四"))
                .appendNext(new Level3AuthLink(3, "王五"));

        authLink.doAuth(10, "1111");

    }

}
