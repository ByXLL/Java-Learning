package com.nowcoder.community.dao.elasticsearch;

import com.nowcoder.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * 帖子 es 接口
 * 继承  ElasticsearchRepository <实体名，主键类型>
 */
//@Deprecated
////@Repository     // Repository spring 的dao层注解
//public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {
//    // ElasticsearchRepository 内部定义了 增删改查 我们只需要在实现类去实现
//
//}
