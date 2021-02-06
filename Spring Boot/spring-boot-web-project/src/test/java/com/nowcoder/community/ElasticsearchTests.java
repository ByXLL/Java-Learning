package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.ContextConfiguration;

/**
 * es  测试类
 */
//@SpringBootTest
//@ContextConfiguration(classes = CommunityApplication.class)
//public class ElasticsearchTests {
//    @Autowired
//    private DiscussPostMapper discussPostMapper;
//
//    @Autowired
//    private DiscussPostRepository discussPostRepository;
//
//    @Autowired
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//
//    @Test
//    public void testInert() {
//        discussPostRepository.save(discussPostMapper.selectDiscussPostById(241));
//        discussPostRepository.save(discussPostMapper.selectDiscussPostById(242));
//        discussPostRepository.save(discussPostMapper.selectDiscussPostById(243));
//    }
//}
