package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /***
     * 获取所有帖子
     * @param userId    用户id针对为用户中心查看自己帖子 可以为空
     * @param offset    分页 偏移量 起始行行号
     * @param limit     分页 条数
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /***
     * 获取帖子行数  @Param注解用于给参数取别名,如果只有一个参数,并且在sql的<if>里使用,则必须加别名.
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);

}
