package com.mali.travelstrategy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mali.travelstrategy.entity.User;
import com.mali.travelstrategy.entity.Version;
import com.mali.travelstrategy.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户表 Mapper 接口
 * @author By-mali
 */
@Repository
public interface VersionMapper extends BaseMapper<Version> {

}
