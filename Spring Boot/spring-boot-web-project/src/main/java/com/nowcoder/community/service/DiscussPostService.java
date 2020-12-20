package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * service 层
 */
@Service
public class DiscussPostService {
    // 注入dao层 mapprer
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /***
     * 业务方法 获取帖子列表
     * @param userId    用户id
     * @param offset    偏移量
     * @param limit     行数
     * @return  列表集合
     */
    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        // 使用mapper的方法 获得数据 并返回 到 controller
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    /***
     * 业务方法 获取帖子个数
     * @param userId    用户id
     * @return 列表集合
     */
    public int selectDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
