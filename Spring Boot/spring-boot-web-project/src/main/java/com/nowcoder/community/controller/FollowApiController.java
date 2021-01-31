package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.Event;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.event.EventProducer;
import com.nowcoder.community.service.FollowService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 关注
 */
@RestController
public class FollowApiController extends ApiController implements CommunityConstant {
    private final FollowService followService;
    private final HostHolder hostHolder;
    private final EventProducer eventProducer;

    public FollowApiController(FollowService followService, HostHolder hostHolder, EventProducer eventProducer) {
        this.followService = followService;
        this.hostHolder = hostHolder;
        this.eventProducer = eventProducer;
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
        // 触发关注事件
        Event event = new Event()
                .setTopic(TOPIC_FOLLOW)
                .setUserId(hostHolder.getUser().getId())
                .setEntityType(entityType)
                .setEntityId(entityId)
                .setEntityUserId(entityId);
        eventProducer.fireEvent(event);
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
