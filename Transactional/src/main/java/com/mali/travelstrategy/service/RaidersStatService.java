package com.mali.travelstrategy.service;

import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.RaidersStat;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 攻略点赞表 服务类
 * @author By-mali
 */
public interface RaidersStatService extends IService<RaidersStat> {
    void add(RaidersStat raidersStat);
}
