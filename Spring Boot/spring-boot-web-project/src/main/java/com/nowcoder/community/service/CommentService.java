package com.nowcoder.community.service;

import com.nowcoder.community.dao.CommentMapper;
import com.nowcoder.community.entity.Comment;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@Service
public class CommentService implements CommunityConstant {
    private final DiscussPostService discussPostService;
    private final SensitiveFilter sensitiveFilter;
    private final CommentMapper commentMapper;
    public CommentService(CommentMapper commentMapper, SensitiveFilter sensitiveFilter, DiscussPostService discussPostService) {
        this.commentMapper = commentMapper;
        this.sensitiveFilter = sensitiveFilter;
        this.discussPostService = discussPostService;
    }

    /***
     * 通过实体查询 评论集合
     * @param entityType    实体类型
     * @param entityId      实体id
     * @param offset     偏移量
     * @param limit     行数
     * @return      评论集合
     */
    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentByEntity(entityType,entityId,offset,limit);
    }

    /**
     * 查找评论个数
     * @param entityType    实体类型
     * @param entityId      实体ID
     * @return      个数
     */
    public int findCommentCount(int entityType, int entityId){
        return commentMapper.selectCountByEntity(entityType,entityId);
    }

    /**
     * 添加评论    事务管理
     * @param comment   评论实体
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int addComment(Comment comment){
        if(comment == null) {
            throw  new IllegalArgumentException("参数不能为空");
        }
        // html 代码过滤
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        // 敏感词过滤
        comment.setContent(sensitiveFilter.filterKeyWord(comment.getContent()));
        int rows = commentMapper.insertComment(comment);

        // 判断当前新增的评论是对帖子的 更新评论数量 只能是帖子的数量
        if(comment.getEntityType() == ENTITY_TYPE_POST) {
            int count = commentMapper.selectCountByEntity(comment.getEntityType(), comment.getEntityId());
            discussPostService.updateCommentCount(comment.getId(), count);
        }

        return rows;
    }

    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }
}
