package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {
    @Autowired
    private MailClient mailClient;

    // 注入模板引擎
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSendMail() {
        mailClient.sendMail("by_x11@163.com","TEST","Welocome");
    }

    @Test
    public void testSendHtmlMail() {
        Context context = new Context();
        context.setVariable("username","张三");
        // 设置模板文件
        String htmlContent = templateEngine.process("/mail/demo",context);
        System.out.println(htmlContent);
        mailClient.sendMail("by_x11@163.com","TEST",htmlContent);
    }
}
