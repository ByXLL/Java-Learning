package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@ContextConfiguration
@SpringBootTest
class CommunityApplicationTests {
//    @Autowired
//    private DiscussPostMapper discussPostMapper;
//    @Autowired
//    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

//    @Test
//    public void testDiscussPostMapper() {
//        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0,0,10);
//        for (DiscussPost post :list){
//            System.out.println(post);
//        }
//    }
}
