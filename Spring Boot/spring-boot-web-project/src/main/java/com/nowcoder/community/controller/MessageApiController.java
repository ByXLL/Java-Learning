package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.MessageService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 私信
 */
@RestController
public class MessageApiController extends ApiController {
    private final MessageService messageService;
    private final HostHolder hostHolder;
    private final UserService userService;
    public MessageApiController(MessageService messageService, HostHolder hostHolder, UserService userService) {
        this.messageService = messageService;
        this.hostHolder = hostHolder;
        this.userService = userService;
    }

    /**
     * 发送 私信
     * @param toName
     * @param content
     * @return
     */
    @PostMapping("/letter/send")
    public ApiResult sendLetter(String toName, String content) {
        User target = userService.findUserByName(toName);
        if(target == null ){
            return Error("目标用户不存在");
        }
        Message message = new Message();
        message.setFromId(hostHolder.getUser().getId());
        message.setToId(target.getId());
        // 判断 会话id 先后顺序
        if(message.getFromId() < message.getToId()) {
            message.setConversationId(message.getFromId() + "_" + message.getToId());
        }else {
            message.setConversationId(message.getToId() + "_" + message.getFromId());
        }
        message.setContent(content);
        message.setCreateTime(new Date());
        // 可能会存在异常 交给全局异常处理
        messageService.addMessage(message);
        return Successed("成功");
    }


}
