package com.mali.travelstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mali.travelstrategy.dto.UserParam;
import com.mali.travelstrategy.dto.UserPasswordDto;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.RaidersStat;
import com.mali.travelstrategy.entity.User;
import com.mali.travelstrategy.entity.UserAttention;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.mapper.RaidersStatMapper;
import com.mali.travelstrategy.mapper.UserAttentionMapper;
import com.mali.travelstrategy.mapper.UserMapper;
import com.mali.travelstrategy.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mali.travelstrategy.util.CommUtils;
import com.mali.travelstrategy.util.Constants;
import com.mali.travelstrategy.vo.UserVO;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户表 服务实现类
 * @author By-mali
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RaidersStatMapper raidersStatMapper;

    @Autowired
    private UserAttentionMapper userAttentionMapper;

}
