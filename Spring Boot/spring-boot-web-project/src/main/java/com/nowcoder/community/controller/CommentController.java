package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private final HostHolder hostHolder;
    private CommentService commentService;
    public CommentController(HostHolder hostHolder) {
        this.hostHolder = hostHolder;
    }

    @Autowired
    public CommentController(HostHolder hostHolder, CommentService commentService) {
        this.hostHolder = hostHolder;
        this.commentService = commentService;
    }

    @PostMapping("/add/{discussPostId}")
    public String addComment(@PathVariable("discussPostId") int discussPostId, Comment comment){
        // 获取评论的用户id 由当前登录用户信息拿取 可能为空  后面全局处理
        comment.setUserId(hostHolder.getUser().getId());
        comment.setStatus(0);
        comment.setCreateTime(new Date());
        commentService.addComment(comment);
        return "redirect:/discuss/detail/" + discussPostId;
    }

}
