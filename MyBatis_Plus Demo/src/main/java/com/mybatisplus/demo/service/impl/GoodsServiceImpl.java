package com.mybatisplus.demo.service.impl;

import com.mybatisplus.demo.entity.Goods;
import com.mybatisplus.demo.mapper.GoodsMapper;
import com.mybatisplus.demo.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author By-Lin
 * @since 2021-04-16
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}
