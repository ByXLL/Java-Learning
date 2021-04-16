package com.mybatisplus.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.demo.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户 mapper 接口
 * 在 mybatis_plus中 我们只需要在 mapper 中去继承 BaseMapper 即可
 * @author By-Lin
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 这个时候简单的增删改查方法 已经完成
     * 当然我们也可以在这里去根据自己的业务场景去编写自己的方法
     */
}
