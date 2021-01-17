package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;

@Service
public class AlphaService {
    private final TransactionTemplate transactionTemplate;
    private final DiscussPostMapper discussPostMapper;
    private final UserMapper userMapper;
    private final DiscussPostService discussPostService;
    private final UserService userService;
    public AlphaService(DiscussPostService discussPostService, UserService userService, UserMapper userMapper, DiscussPostMapper discussPostMapper, TransactionTemplate transactionTemplate) {
        this.discussPostService = discussPostService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.discussPostMapper = discussPostMapper;
        this.transactionTemplate = transactionTemplate;
    }

    /***
    * 测试事务管理
     * 通过 添加事务的注解，将当前方法作为一个整体，当这里面存在代码报错，则回滚已经插入的数据
     * isolation  级别为 已提交的数据
     * propagation  事务的传播机制
     *              REQUIRED    支持当前事务  A调B  如果不存在则创建新事务
     *              REQUIRES_NEW    创建一个新事务，并且暂停大哥前事务(外部事务)
     *              NESTED          如果当前存在事务(外部事务)，则嵌套在外部事物中执行
    */@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)

    public Object save1() {
        // 模拟新增用户
        User user = new User();
        user.setUsername("测试事务");
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://image.nowcoder.com/head/999t.png");
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        // 新增帖子
        DiscussPost post = new DiscussPost();
        post.setUserId(user.getId());
        post.setTitle("你好");
        post.setContent("我是新人!");
        post.setCreateTime(new Date());
        discussPostMapper.insertDiscussPost(post);

        Integer.valueOf("abc");

        return "ok";
    }


    public Object save2() {
        // 设置传播级别
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
        // 设置传播机制
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        // 利用它执行sql 保证事务    参数是一个回调接口
        return transactionTemplate.execute(new TransactionCallback<Object>() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                // 模拟新增用户
                User user = new User();
                user.setUsername("测试事务");
                user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
                user.setPassword(CommunityUtil.md5("123" + user.getSalt()));
                user.setEmail("test@qq.com");
                user.setHeaderUrl("http://image.nowcoder.com/head/999t.png");
                user.setCreateTime(new Date());
                userMapper.insertUser(user);

                // 新增帖子
                DiscussPost post = new DiscussPost();
                post.setUserId(user.getId());
                post.setTitle("你好");
                post.setContent("我是新人!");
                post.setCreateTime(new Date());
                discussPostMapper.insertDiscussPost(post);

                Integer.valueOf("abc");
                return "ok";
            }
        });
    }
}
