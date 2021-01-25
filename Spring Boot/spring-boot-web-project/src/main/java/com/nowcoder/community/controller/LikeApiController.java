package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 点赞 api 控制器
 */
@RestController
public class LikeApiController extends ApiController {
    private final LikeService likeService;
    private final HostHolder hostHolder;

    public LikeApiController(HostHolder hostHolder, LikeService likeService) {
        this.hostHolder = hostHolder;
        this.likeService = likeService;
    }
    @ResponseBody
    @PostMapping("/like")
    public ApiResult like(int entityType, int entityId, int entityUserId) {
        User user = hostHolder.getUser();
        if(user == null) {
            return Error("请登录");
        }
        // 点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType,entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 组装返回的数据
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus",likeStatus);

//        if() {
//
//        }
        return Successed("点赞成功",map);
    }
}
