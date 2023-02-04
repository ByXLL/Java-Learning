package com.mali.travelstrategy.mapper;

import com.mali.travelstrategy.entity.UserAttention;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户关注表 Mapper 接口
 * @author By-mali
 */
@Repository
public interface UserAttentionMapper extends BaseMapper<UserAttention> {
    /**
     * 通过用户id查询 该用户关注的所有用户
     * @param userId        用户id
     * @return              列表数据
     */
    List<Object> selectWatchingUsers(Integer userId);
}
