package com.bro.demo.service.impl;

import com.bro.demo.entity.Goods;
import com.bro.demo.mapper.GoodsMapper;
import com.bro.demo.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author By_Lin
 * @since 2021-04-26
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
