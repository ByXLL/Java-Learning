package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/***
 * service 层
 */
@Service
public class DiscussPostService {
    // 注入dao层 mapprer
    @Autowired
    private DiscussPostMapper discussPostMapper;

    // 注入敏感词过滤器
    @Autowired
    private SensitiveFilter sensitiveFilter;

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

    /***
     * 添加 帖子
     * @param discussPost   帖子实体
     * @return
     */
    public Integer addDiscussPost(DiscussPost discussPost) {
        if(discussPost == null) {
            throw new IllegalArgumentException("参数不能为空");
        }

        // 转义html标记 防止xss攻击
        discussPost.setTitle(HtmlUtils.htmlEscape(discussPost.getTitle()));
        discussPost.setContent(HtmlUtils.htmlEscape(discussPost.getContent()));

        // 敏感词过滤
        discussPost.setTitle(sensitiveFilter.filterKeyWord(discussPost.getTitle()));
        discussPost.setContent(sensitiveFilter.filterKeyWord(discussPost.getContent()));
        return discussPostMapper.insertDiscussPost(discussPost);
    }

    /**
     * 通过id 获取帖子详情
     * @param id    帖子id
     * @return
     */
    public DiscussPost findDiscussPostById(int id) {
        return discussPostMapper.selectDiscussPostById(id);
    }
}
