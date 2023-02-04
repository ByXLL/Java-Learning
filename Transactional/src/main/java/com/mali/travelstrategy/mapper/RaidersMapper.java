package com.mali.travelstrategy.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mali.travelstrategy.entity.Raiders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mali.travelstrategy.vo.RaidersDetailsVO;
import com.mali.travelstrategy.vo.RaidersStatVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 攻略表 Mapper 接口
 * @author By-mali
 */
@Repository
public interface RaidersMapper extends BaseMapper<Raiders> {

    /**
     * 查询攻略详情
     * @param page  分页器对象
     * @param ew    查询条件
     * @return      响应数据
     */
    IPage<RaidersDetailsVO> selectRaidersDetails(IPage<RaidersDetailsVO> page, @Param(Constants.WRAPPER) QueryWrapper<RaidersDetailsVO> ew);

    /**
     * 查询攻略点赞列表
     * @param raidersId     攻略id
     * @return              点赞列表
     */
    List<RaidersStatVo> findStatListByRaidersId(Integer raidersId);
}
