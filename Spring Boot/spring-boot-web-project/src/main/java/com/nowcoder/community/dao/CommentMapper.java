package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 通过实体查询评论
    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);
    // 通过实体查询 评论 个数
    int selectCountByEntity(int entityType, int entityId);
    // 插入评论
    int insertComment(Comment comment);
    // 根据id 查询
    Comment selectCommentById(int id);

}
