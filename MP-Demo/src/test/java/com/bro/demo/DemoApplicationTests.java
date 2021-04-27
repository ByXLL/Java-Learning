package com.bro.demo;



import com.bro.demo.entity.Goods;
import com.bro.demo.mapper.GoodsMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void testSql() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        System.out.println();
    }

    @Test
    void testSql1() {
        List<Goods> goodsList = goodsMapper.testSql();
        System.out.println();
    }
}
