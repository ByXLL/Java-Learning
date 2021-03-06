package com.nowcoder.community.controller;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.FollowService;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/user")
public class UserController implements CommunityConstant {
    // 注入日志组件
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 注入配置文件中申明的配置
    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private final FollowService followService;
    private final LikeService likeService;
    private final HostHolder hostHolder;

    private final UserService userService;

    public UserController(HostHolder hostHolder, UserService userService, LikeService likeService, FollowService followService) {
        this.hostHolder = hostHolder;
        this.userService = userService;
        this.likeService = likeService;
        this.followService = followService;
    }

    @LoginRequired
    @GetMapping("/setting")
    public String getSettingPage() {
        return "/site/setting";
    }

    @LoginRequired
    @PostMapping("/upload")
    public String uploadImg(MultipartFile headerImage, Model model) {
        if(headerImage == null) {
            model.addAttribute("error","请选择需要上传的文件");
            return "/site/setting";
        }
        // 获取原始文件名
        String fileName = headerImage.getOriginalFilename();
        // 获取原始的文件类型
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffixName)) {
            model.addAttribute("error","文件格式不正确");
            return "/site/setting";
        }
        // 生成随机文件名
        fileName = CommunityUtil.generateUUID() + suffixName;
        // 确认存放路径
        File dest = new File( uploadPath + "/" + fileName);
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败：  "+e.getMessage());
            throw new RuntimeException("上传文件失败，服务器异常",e);
        }

        User user = hostHolder.getUser();
        String userHeaderUrl = domain + contextPath + "/user/header/" + fileName;
        // 更新当前用户的头像地址
        userService.updateHeader(user.getId(),userHeaderUrl);
        return  "redirect:/index";
    }

    /**
     * 根据文件名获取文件    通过response流去输出
     * @param fileName      文件名
     * @param response      响应体
     */
    @GetMapping("/header/{fileName}")
    public void getHeader(@PathVariable("fileName") String fileName, HttpServletResponse response) {
        // 获取服务器存放的路径
        fileName = uploadPath + "/" + fileName;
        // 获取文件的后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        // 响应 图片
        // 设置响应头
        response.setContentType("image/" + suffixName);
        // 通过响应头的流去输出图片
        try(
            // 使用java7 语法，在try中声明，在finney中会自动使用close方法 关闭流
            FileInputStream fileInputStream = new FileInputStream(fileName);
            OutputStream outputStream =  response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer,0,b);
            }
        } catch (IOException e) {
            logger.error("读取文件失败：  ",e.getMessage());
        }
    }

    /**
     * 用户信息页
     * @param userId    用户id
     * @param model     模型
     * @return
     */
    @GetMapping("/profile/{userId}")
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        // 用户
        User user = userService.findUserById(userId);
        if(user == null) {
            throw new RuntimeException("该用户不存在");
        }
        // 点赞数量
        int likeCount = likeService.findUserLikeCount(userId);
        // 关注数量
        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
        // 粉丝数量
        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
        // 是否已关注
        boolean hasFollowed = false;
        if(hostHolder.getUser() != null){
            hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(),ENTITY_TYPE_USER,userId);
        }
        model.addAttribute("user",user);
        model.addAttribute("likeCount",likeCount);
        model.addAttribute("followeeCount",followeeCount);
        model.addAttribute("followerCount",followerCount);
        model.addAttribute("hasFollowed",hasFollowed);
        return "/site/profile";
    }
}
