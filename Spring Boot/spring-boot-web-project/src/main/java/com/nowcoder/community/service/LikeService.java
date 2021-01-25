package com.nowcoder.community.service;

import com.nowcoder.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 * 点赞 服务
 */
@Service
public class LikeService {
    private final RedisTemplate redisTemplate;

    public LikeService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 点赞
     * @param userId    用户id
     * @param entityType    点赞的实体类型
     * @param entityUserId    评论的人的id
     * @param entityId      实体id
     */
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        // 事务管理
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                // 拼接实体的key
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                // 拼接用户的key
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);
                // 判断当前用户对这个 评论实体有没有点过赞
                boolean isMember = redisOperations.opsForSet().isMember(entityLikeKey,userId);
                // 开启事务
                redisOperations.multi();
                // 判断是否 已经点赞
                if(isMember) {
                    redisOperations.opsForSet().remove(entityLikeKey,userId);
                    redisOperations.opsForValue().decrement(userLikeKey);
                }else {
                    redisOperations.opsForSet().add(entityLikeKey,userId);
                    redisOperations.opsForValue().increment(userLikeKey);
                }

                // 执行事务
                return redisOperations.exec();
            }
        });
    }

    /**
     * 查询某实体点赞的数量
     * @param entityType    实体类型
     * @param entityId      实体iid
     * @return      个数
     */
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    /**
     * 查询某人对某实体的点赞状态
     * @param userId    用户id
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return  0/1
     */
    public int findEntityLikeStatus(int userId, int entityType, int entityId ) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey,userId) ? 1 : 0;
    }

    /**
     * 获取用户点赞的数量
     * @param userId    用户id
     * @return
     */
    public int findUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : 1;
    }
}
