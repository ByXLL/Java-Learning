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
}
