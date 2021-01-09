package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;

// 帖子 视图控制器
@RestController     // 声明返回的是json 当然也可以在 指定的方法前使用 @ResponseBody 声明
@RequestMapping("/discuss")
public class DiscussPostApiController extends ApiController {
    /**
     * 将通过注释 注入的service 改成使用构造函数注入 解决耦合问题
     */
    private final DiscussPostService discussPostService;
    private final HostHolder hostHolder;
    private final UserService userService;
    public DiscussPostApiController(DiscussPostService discussPostService, HostHolder hostHolder, UserService userService) {
        this.discussPostService = discussPostService;
        this.hostHolder = hostHolder;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/add")
    public ApiResult addDiscuss(String title, String content) {
        // 先去判断是否登录
        User user = hostHolder.getUser();
        if (user == null) {
            return Error("您还没有登录");
            //  return CommunityUtil.getJsonString(403,"您还没有登录");
        }
        DiscussPost data = new DiscussPost();
        data.setUserId(user.getId());
        data.setTitle(title);
        data.setContent(content);
        data.setCreateTime(new Date());
        discussPostService.addDiscussPost(data);

        // 这里使用 我们定义的 成功类 而不使用封装的fastJson 避免了前端接收的不是json
        return Successed("保存成功");
        //return CommunityUtil.getJsonString(0,"保存成功");
    }
}
