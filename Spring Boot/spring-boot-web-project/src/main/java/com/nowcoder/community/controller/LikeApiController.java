package com.nowcoder.community.controller;

import com.nowcoder.community.data.ApiResult;
import com.nowcoder.community.entity.Event;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.event.EventProducer;
import com.nowcoder.community.service.LikeService;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LikeApiController extends ApiController implements CommunityConstant {
    private final LikeService likeService;
    private final HostHolder hostHolder;
    private final EventProducer eventProducer;

    public LikeApiController(HostHolder hostHolder, LikeService likeService, EventProducer eventProducer) {
        this.hostHolder = hostHolder;
        this.likeService = likeService;
        this.eventProducer = eventProducer;
    }
    @ResponseBody
    @PostMapping("/like")
    public ApiResult like(int entityType, int entityId, int entityUserId,int postId) {
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

        // 触发点赞事件  点赞的时候 触发通知 取消点赞的时候不触发通知
        if(likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId",postId);
            // 触发点赞的 事件
            eventProducer.fireEvent(event);
        }


        return Successed("点赞成功",map);
    }
}
