package com.nowcoder.community.controller;

import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.CommentService;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {
    private final CommentService commentService;
    private final DiscussPostService discussPostService;
    private final UserService userService;
    public DiscussPostController(DiscussPostService discussPostService, UserService userService, CommentService commentService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page) {
        // 帖子
        DiscussPost discussPost =  discussPostService.findDiscussPostById(discussPostId);
        // 作者
        User user = userService.findUserById(discussPost.getUserId());

        // 评论的分页信息
        page.setLimit(5);
        page.setPath("/discuss/detail/" + discussPostId);
        page.setRows(discussPost.getCommentCount());

        // 评论的列表
        List<Comment> commentList =  commentService.findCommentsByEntity(ENTITY_TYPE_POST, discussPost.getId(), page.getOffset(), page.getLimit());
        // 处理  评论的视图模型
        List<Map<String,Object>> commentVOList = new ArrayList<>();
        if (!commentList.isEmpty()) {
            for (Comment commentItem : commentList) {
                // 评论的视图模型
                Map<String,Object> commentVOItem = new HashMap<>();
                // 评论信息
                commentVOItem.put("comment", commentItem);
                // 用户信息
                commentVOItem.put("user", userService.findUserById(commentItem.getUserId()));
                // 回复列表
                List<Comment> replyList = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, commentItem.getId(), 0, Integer.MAX_VALUE);
                // 回复的视图模型
                List<Map<String,Object>> replyVOList = new ArrayList<>();
                if(!replyList.isEmpty()) {
                    for (Comment replyItem : replyList) {
                        Map<String, Object> replyVOItem = new HashMap<>();
                        // 回复 内容
                        replyVOItem.put("reply",replyItem);
                        // 回复 作者
                        replyVOItem.put("user",userService.findUserById(replyItem.getUserId()));
                        // 回复的目标
                        User targetUser = replyItem.getTargetId() == 0 ? null : userService.findUserById(replyItem.getTargetId());
                        replyVOItem.put("target",targetUser);
                        replyVOList.add(replyVOItem);
                    }
                }
                commentVOItem.put("replys",replyVOList);
                // 回复数量
                int replyCount =  commentService.findCommentCount(ENTITY_TYPE_COMMENT,commentItem.getId());
                commentVOItem.put("replyCount",replyCount);
                commentVOList.add(commentVOItem);
            }
        }

        model.addAttribute("post",discussPost);
        model.addAttribute("user",user);
        model.addAttribute("comments",commentVOList);
        return "/site/discuss-detail";
    }
}
