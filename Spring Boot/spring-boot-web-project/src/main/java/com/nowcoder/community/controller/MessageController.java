package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Message;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.MessageService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 私信
 */
@Controller
public class MessageController {
    private final MessageService messageService;
    private final HostHolder hostHolder;
    private final UserService userService;
    public MessageController(MessageService messageService, HostHolder hostHolder, UserService userService) {
        this.messageService = messageService;
        this.hostHolder = hostHolder;
        this.userService = userService;
    }

    @GetMapping("/letter/list")
    public String getLetterPage(Model model, Page page) {
        User user =hostHolder.getUser();
        // 分页信息
        page.setLimit(5);
        page.setPath("/letter/list");
        page.setRows(messageService.findConversationCount(user.getId()));
        // 会话列表
        List<Message> conversationList = messageService.findConversations(user.getId(), page.getOffset(),page.getLimit());
        List<Map<String,Object>> conversations = new ArrayList<>();
        if(conversations != null) {
            for (Message message:conversationList) {
                Map<String, Object> item = new HashMap<>();
                item.put("conversation",message);
                item.put("letterCount", messageService.findLetterCount(message.getConversationId()));
                item.put("unreadCount", messageService.findLetterUnreadCount(user.getId(), message.getConversationId()));
                int targetId = user.getId() == message.getFromId() ? message.getToId() : message.getFromId();
                item.put("target",userService.findUserById(targetId));
                conversations.add(item);
            }
        }
        model.addAttribute("conversations",conversations);
        // 查询未读消息数量
        int letterUnreadCount = messageService.findLetterUnreadCount(user.getId(), null);
        model.addAttribute("letterUnreadCount",letterUnreadCount);

        return "/site/letter";
    }

    @GetMapping("/letter/detail/{conversationId}")
    public String getLetterDetail(@PathVariable("conversationId") String conversationId, Page page, Model model){
        // 分页信息
        page.setLimit(5);
        page.setPath("/letter/detail/" + conversationId);
        page.setRows(messageService.findLetterCount(conversationId));
        // 私信列表
        messageService.findLetters(conversationId, page.getOffset(), page.getLimit());
        List<Message> letterList = messageService.findLetters(conversationId, page.getOffset(), page.getLimit());
        List<Map<String,Object>> letters = new ArrayList<>();
        if(letterList != null) {
            for (Message message : letterList) {
                Map<String,Object> map = new HashMap<>();
                map.put("letter",message);
                map.put("fromUser",userService.findUserById(message.getFromId()));
                letters.add(map);
            }
        }
        model.addAttribute("letters",letters);
        // 获取私信目标
        model.addAttribute("target",getLetterTarget(conversationId));

        // 设置已读
        List<Integer> ids = getLetterIds(letterList);
        if(!ids.isEmpty()) {
            messageService.readMessage(ids);
        }
        return "/site/letter-detail";
    }


    /**
     * 获取 私信目标
     * @param conversationId    id
     * @return
     */
    private User getLetterTarget(String conversationId) {
        // 拆分id
        String[] ids = conversationId.split("_");
        int id0 = Integer.parseInt(ids[0]);
        int id1 = Integer.parseInt(ids[1]);

        if(hostHolder.getUser().getId() == id0) {
            return userService.findUserById(id1);
        }else {
            return userService.findUserById(id0);
        }
    }

    // 从消息列表里提取未读消息 id集合
    private List<Integer> getLetterIds(List<Message> letterList) {
        List<Integer> ids = new ArrayList<>();
        if(ids != null) {
            for (Message message : letterList){
                // 判断当前登录的是接收者
                if(hostHolder.getUser().getId() == message.getToId() && message.getStatus() == 0){
                    ids.add(message.getId());
                }
            }
        }
        return ids;
    }
}
