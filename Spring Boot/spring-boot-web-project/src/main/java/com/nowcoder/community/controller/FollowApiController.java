package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.FollowService;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关注
 */
@RestController
public class FollowApiController extends ApiController {
    private final FollowService followService;

    private final HostHolder hostHolder;

    public FollowApiController(FollowService followService, HostHolder hostHolder) {
        this.followService = followService;
        this.hostHolder = hostHolder;
    }

    /**
     * 关注
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return
     */
    @PostMapping("/follow")
    public ApiResult follow(int entityType, int entityId){
        User user = hostHolder.getUser();
        if(user == null) {
            return Error("请先登录");
        }
        followService.follow(user.getId(), entityType, entityId);
        return Successed("关注成功");
    }

    /**
     * 取消关注
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return
     */
    @PostMapping("/unfollow")
    public ApiResult unFollow(int entityType, int entityId){
        User user = hostHolder.getUser();
        if(user == null) {
            return Error("请先登录");
        }
        followService.unFollow(user.getId(), entityType, entityId);
        return Successed("取消成功");
    }
}
