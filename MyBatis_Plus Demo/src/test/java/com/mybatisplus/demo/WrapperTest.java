package com.mybatisplus.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatisplus.demo.entity.Goods;
import com.mybatisplus.demo.entity.User;
import com.mybatisplus.demo.mapper.GoodsMapper;
import com.mybatisplus.demo.mapper.UserMapper;
import com.mybatisplus.demo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * Wrapper 测试类
 */
@SpringBootTest
public class WrapperTest {

    /**
     *  在我们添加的 UserMapper 接口 中继承了BaseMapper
     *  所有的简单的增删改查方法都来自父类，我们也可以去编写我们自己的扩展
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 通过 构造器 按条件查询
     */
    @Test
    void selectAllByWrapper() {
        // 查询name不为空，并且邮箱不为空，年龄大于等于20的
        // new 一个 QueryWrapper 泛型为 实体
        // 根据筛选条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                    .isNotNull("email")
                    .between("age",20,30);
        userMapper.selectList(queryWrapper).forEach(System.out::println);
    }

    /**
     * 模糊查询
     */
    @Test
    void selectLike() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","狗")
                    .likeRight("email","163");
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 子查询
     */
    @Test
    void inSql() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from user where id<3");
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    void selectGoods() {
        List<Goods> goodsList = goodsMapper.selectList(null);
        goodsList.forEach(System.out::println);
    }

    @Test
    void testMySelect() {
        List<UserVO> userVOList = userMapper.mySelectUserList();
        userVOList.forEach(System.out::println);
    }
}
