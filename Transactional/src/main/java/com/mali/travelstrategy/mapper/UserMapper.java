package com.mali.travelstrategy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mali.travelstrategy.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mali.travelstrategy.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户表 Mapper 接口
 * @author By-mali
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用户列表
     * @param page  分页器对象
     * @param ew    查询条件
     * @return      响应数据
     */
    IPage<UserVO> selectUserList(IPage<UserVO> page, @Param(Constants.WRAPPER) QueryWrapper<UserVO> ew);
}
