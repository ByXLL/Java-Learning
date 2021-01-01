package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 邮件服务
 */
@Component
public class MailClient {
    // 声明日志 使用当前类命名
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    // 注入邮箱服务
    @Autowired
    private JavaMailSender mailSender;

    // 将配置文件中的用户名的值导进来
    @Value("${spring.mail.username}")
    private String from;

    /***
     * 发送方法
     * @param to    目标邮箱
     * @param subject   标题
     * @param content   内容
     */
    public void sendMail(String to, String subject, String content) {
        try {
            // 构建一个 mimeMailMessage
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            // 通过构建类 构建详细配置
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            // 设置发送人
            mimeMessageHelper.setFrom(from);
            // 设置目标地址
            mimeMessageHelper.setTo(to);
            // 设置标题
            mimeMessageHelper.setSubject(subject);
            // 设置内容 并允许发送html
            mimeMessageHelper.setText(content,true);
        } catch (MessagingException e) {
            logger.error("邮件发送失败："+ e.getMessage());
        }
    }
}
