package com.nowcoder.community.util;

/**
 * 生成 Redis key的工具
 */
public class RedisKeyUtil {
    // 用户拼接key
    private static final String SPLIT = ":";
    // 实体的前缀
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    // 用户的前缀
    private static final String PREFIX_USER_LIKE = "like:user";

    // 我的关注的前缀
    private static final String PREFIX_FOLLOWEE = "followee";
    // 被关注人的粉丝的前缀
    private static final String PREFIX_FOLLOWER = "follower";

    /**
     * 某个实体的赞
     * like:entity:entityType:entityId -> set(userId)
     * @param entityType    实体类型
     * @param entityId      实体id
     * @return
     */
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE+SPLIT+entityType+SPLIT+entityId;
    }

    /**
     * 获取某个用户的赞 的key
     * @param userId    用户id
     * @return
     */
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }


    /**
     * 某个用户关注的实体
     * followee:userId:entityType -> zset(entityId,now)
     * @param userId
     * @param entityType
     * @return
     */
    public static String getFolloweeKey(int userId, int entityType) {
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    /**
     * 某个实体拥有的粉丝
     * follower:entityType:entityId -> zset(userId,now)
     * @param entityType
     * @param entityId
     * @return
     */
    public static String getFollowerKey(int entityType, int entityId) {
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }
}
