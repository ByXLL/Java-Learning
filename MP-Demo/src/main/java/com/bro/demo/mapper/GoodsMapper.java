package com.bro.demo.mapper;

import com.bro.demo.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author By_Lin
 * @since 2021-04-26
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> testSql();
}
