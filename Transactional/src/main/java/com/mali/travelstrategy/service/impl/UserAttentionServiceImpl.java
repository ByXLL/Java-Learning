package com.mali.travelstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.RaidersStat;
import com.mali.travelstrategy.entity.User;
import com.mali.travelstrategy.entity.UserAttention;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.mapper.UserAttentionMapper;
import com.mali.travelstrategy.mapper.UserMapper;
import com.mali.travelstrategy.service.UserAttentionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mali.travelstrategy.util.CommUtils;
import com.mali.travelstrategy.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 用户关注表 服务实现类
 * @author By-mali
 */
@Service
public class UserAttentionServiceImpl extends ServiceImpl<UserAttentionMapper, UserAttention> implements UserAttentionService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAttentionMapper userAttentionMapper;


}
