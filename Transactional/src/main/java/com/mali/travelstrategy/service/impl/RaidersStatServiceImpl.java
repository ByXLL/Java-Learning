package com.mali.travelstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.Attractions;
import com.mali.travelstrategy.entity.Raiders;
import com.mali.travelstrategy.entity.RaidersStat;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.exception.LoginException;
import com.mali.travelstrategy.mapper.RaidersMapper;
import com.mali.travelstrategy.mapper.RaidersStatMapper;
import com.mali.travelstrategy.mapper.UserMapper;
import com.mali.travelstrategy.service.RaidersStatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mali.travelstrategy.vo.RaidersStatVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 攻略点赞表 服务实现类
 * @author By-mali
 */
@Service
public class RaidersStatServiceImpl extends ServiceImpl<RaidersStatMapper, RaidersStat> implements RaidersStatService {
    @Autowired
    private RaidersStatMapper raidersStatMapper;

    @Autowired
    private RaidersMapper raidersMapper;

    @Autowired
    private UserMapper userMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(RaidersStat raidersStat) {
        raidersStatMapper.insert(raidersStat);
        throw new LoginException("手动抛出异常");
    }
}
