package com.mali.travelstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.RaidersComment;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.mapper.RaidersCommentMapper;
import com.mali.travelstrategy.service.RaidersCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mali.travelstrategy.vo.RaidersCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 攻略评论表 服务实现类
 * @author By-mali
 */
@Service
public class RaidersCommentServiceImpl extends ServiceImpl<RaidersCommentMapper, RaidersComment> implements RaidersCommentService {

    @Autowired
    private RaidersCommentMapper raidersCommentMapper;

}
