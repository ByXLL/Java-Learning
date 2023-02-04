package com.mali.travelstrategy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mali.travelstrategy.dto.RaidersParam;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.Raiders;
import com.mali.travelstrategy.entity.RaidersStat;
import com.mali.travelstrategy.entity.UserAttention;
import com.mali.travelstrategy.enums.HttpCodeEnum;
import com.mali.travelstrategy.mapper.RaidersMapper;
import com.mali.travelstrategy.mapper.RaidersStatMapper;
import com.mali.travelstrategy.service.RaidersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mali.travelstrategy.vo.RaidersDetailsVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 攻略表 服务实现类
 * @author By-mali
 */
@Service
public class RaidersServiceImpl extends ServiceImpl<RaidersMapper, Raiders> implements RaidersService {

    @Autowired
    private RaidersMapper raidersMapper;

    @Autowired
    private RaidersStatMapper raidersStatMapper;


    @Override
    public void add(Raiders raiders) {
        raidersMapper.insert(raiders);
        System.out.println("RaidersService ----- 执行成功");
    }
}
