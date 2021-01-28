package com.nowcoder.community.service;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 粉丝 服务
 */
@Service
public class FollowService implements CommunityConstant {
    private final UserService userService;
    private final RedisTemplate redisTemplate;

    public FollowService(RedisTemplate redisTemplate, UserService userService) {
        this.redisTemplate = redisTemplate;
        this.userService = userService;
    }

    /**
     * 关注
     * @param userId        用户id
     * @param entityType    实体类型
     * @param entityId      实体id
     */
    public void follow(int userId, int entityType, int entityId) {
        // 处理事务
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);
                String followerKey = RedisKeyUtil.getFollowerKey(entityType,entityId);
                redisOperations.multi();
                redisOperations.opsForZSet().add(followeeKey,entityId,System.currentTimeMillis());
                redisOperations.opsForZSet().add(followerKey,userId, System.currentTimeMillis());
                return redisOperations.exec();
            }
        });
    }

    /**
     * 取消关注
     * @param userId        用户id
     * @param entityType    实体类型
     * @param entityId      实体id
     */
    public void unFollow(int userId, int entityType, int entityId) {
        // 处理事务
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);
                String followerKey = RedisKeyUtil.getFollowerKey(entityType,entityId);
                redisOperations.multi();
                redisOperations.opsForZSet().remove(followeeKey,entityId);
                redisOperations.opsForZSet().remove(followerKey,userId);
                return redisOperations.exec();
            }
        });
    }

    /**
     * 查询关注的实体的数量
     * @param userId        用户id
     * @param entityType    实体类型
     * @return
     */
    public long findFolloweeCount(int userId, int entityType) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);
        return redisTemplate.opsForZSet().zCard(followeeKey);
    }

    /**
     * 查询实体的粉丝数量
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return
     */
    public long findFollowerCount(int entityType, int entityId) {
        String followeeKey = RedisKeyUtil.getFollowerKey(entityType,entityId);
        return redisTemplate.opsForZSet().zCard(followeeKey);
    }

    /**
     * 判断 当前用户是否已关注该实体
     * @param userId        用户id
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return
     */
    public boolean hasFollowed(int userId, int entityType, int entityId) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, entityType);
        // 通过判断是否存在
        return redisTemplate.opsForZSet().score(followeeKey,entityId) != null;
    }

    /**
     * 查询某个用户关注的人
     * @param userId    用户id
     * @param offset    分页 偏移量
     * @param limit     分页 行数
     * @return
     */
    public List<Map<String,Object>> findFollowees(int userId, int offset, int limit) {
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId, ENTITY_TYPE_USER);
        // 查询多条数据 倒序
        Set<Integer> targetIdS = redisTemplate.opsForZSet().reverseRange(followeeKey, offset, offset+limit-1);
        if(targetIdS == null) { return null; }
        List<Map<String,Object>> list = new ArrayList<>();
        for (Integer targetId : targetIdS){
            Map<String,Object> map = new HashMap<>();
            User user = userService.findUserById(targetId);
            // 关注时间
            Double score = redisTemplate.opsForZSet().score(followeeKey,targetId);
            map.put("user",user);
            map.put("followTime",score);
            list.add(map);
        }
        return list;
    }

    /**
     * 查询某个用户的粉丝
     * @param userId    用户id
     * @param offset    分页 偏移量
     * @param limit     分页 行数
     * @return
     */
    public List<Map<String,Object>> findFollowers(int userId, int offset, int limit) {
        String followerKey = RedisKeyUtil.getFollowerKey(ENTITY_TYPE_USER, userId);
        // 查询多条数据 倒序
        Set<Integer> targetIdS = redisTemplate.opsForZSet().reverseRange(followerKey, offset, offset+limit-1);
        if(targetIdS == null) { return null; }
        List<Map<String,Object>> list = new ArrayList<>();
        for (Integer targetId : targetIdS){
            Map<String,Object> map = new HashMap<>();
            User user = userService.findUserById(targetId);
            // 关注时间
            Double score = redisTemplate.opsForZSet().score(followerKey,targetId);
            map.put("user",user);
            map.put("followTime",score);
            list.add(map);
        }
        return list;
    }
}
