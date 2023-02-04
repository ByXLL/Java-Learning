package com.mali.travelstrategy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mali.travelstrategy.entity.RaidersComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mali.travelstrategy.vo.RaidersCommentVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 攻略评论表 Mapper 接口
 * @author By-mali
 */
@Repository
public interface RaidersCommentMapper extends BaseMapper<RaidersComment> {
    /**
     * 查询攻略评论详情
     * @param page  分页器对象
     * @param ew    查询条件
     * @return      响应数据
     */
    IPage<RaidersCommentVO> selectRaidersCommentDetails(IPage<RaidersCommentVO> page, @Param(Constants.WRAPPER) QueryWrapper<RaidersCommentVO> ew);
}
