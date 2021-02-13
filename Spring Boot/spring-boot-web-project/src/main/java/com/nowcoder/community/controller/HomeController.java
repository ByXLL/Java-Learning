package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {
    // 注入service
    private final LikeService likeService;
    private final DiscussPostService discussPostService;
    private final UserService userService;
    public HomeController(DiscussPostService discussPostService, UserService userService, LikeService likeService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
        this.likeService = likeService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model,Page page) {
        // 在spring mvc中 方法调用前 会自动实例化携带的参数 （model，page）
        // 无需再向页面中 put page 就可以直接在页面中使用page
        // 先设置总个数
        page.setRows(discussPostService.selectDiscussPostRows(0));
        // 设置路径
        page.setPath("/index");

        // 查询出所有的数据
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        // 将用户的信息也添加进去
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list != null) {
            // 遍历拿到的每一个贴子信息
            for (DiscussPost post : list) {
                // 创建一个 Map 对象存放数据
                Map<String,Object> map = new HashMap<>();
                // 将原来的数据放到一个属性中
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                // 点赞量
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }
//        System.out.println(discussPosts);
        // 向页面注入数据
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }
    // 没有权限时的提示页面
    @GetMapping("/denied")
    public String getDeniedPage() {
        return "/error/404";
    }

}
