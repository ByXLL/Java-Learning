package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {
    private final DiscussPostService discussPostService;
    private final UserService userService;
    public DiscussPostController(DiscussPostService discussPostService, UserService userService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
    }

    @GetMapping("/detail")
    public String getDiscussPost(@PathParam("discussPostId") int discussPostId, Model model) {
        System.out.println(discussPostId);
        DiscussPost discussPost =  discussPostService.findDiscussPostById(discussPostId);
        User user = userService.findUserById(discussPost.getUserId());
        model.addAttribute("post",discussPost);
        model.addAttribute("user",user);
        return "/site/discuss-detail";
    }
}
