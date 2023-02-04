package com.mali.travelstrategy.service;

import com.mali.travelstrategy.dto.RaidersParam;
import com.mali.travelstrategy.entity.ApiResult;
import com.mali.travelstrategy.entity.Raiders;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 攻略表 服务类
 * @author By-mali
 */
public interface RaidersService extends IService<Raiders> {
    void add(Raiders raiders);
}
