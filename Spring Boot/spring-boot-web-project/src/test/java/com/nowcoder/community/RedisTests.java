package com.nowcoder.community;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString() {
        String key = "test:count";
        // 设置值
        redisTemplate.opsForValue().set(key,1);

        // 取数据
        System.out.println(redisTemplate.opsForValue().get(key));
        // count ++
        System.out.println(redisTemplate.opsForValue().increment(key));
        // count --
        System.out.println(redisTemplate.opsForValue().decrement(key));
    }

    @Test
    public void testHash() {
        String key = "test:user";
        redisTemplate.opsForHash().put(key,"id",1);
        redisTemplate.opsForHash().put(key,"name","张三");

        System.out.println(redisTemplate.opsForHash().get(key,"id"));
        System.out.println(redisTemplate.opsForHash().get(key,"name"));
    }

    @Test
    public void testList() {
        String key = "test:ids";
        redisTemplate.opsForList().leftPush(key,101);
        redisTemplate.opsForList().leftPush(key,102);
        redisTemplate.opsForList().leftPush(key,103);

        System.out.println(redisTemplate.opsForList().size(key));
        System.out.println(redisTemplate.opsForList().index(key,0));
        System.out.println(redisTemplate.opsForList().range(key,0,2));
        System.out.println(redisTemplate.opsForList().leftPop(key));
    }

   @Test
   public void testSets() {
        String key = "test:teachers";
        redisTemplate.opsForSet().add(key,"张三","李四","王五","赵六");
        System.out.println(redisTemplate.opsForSet().size(key));
       System.out.println(redisTemplate.opsForSet().pop(key));
       System.out.println(redisTemplate.opsForSet().members(key));
   }

   @Test
    public void testSortedSets() {
        String key = "test:students";
        redisTemplate.opsForZSet().add(key,"张三",100);
        redisTemplate.opsForZSet().add(key,"李四",95);
        redisTemplate.opsForZSet().add(key,"王五",75);
        redisTemplate.opsForZSet().add(key,"赵六",85);
        System.out.println(redisTemplate.opsForZSet().zCard(key));
        System.out.println(redisTemplate.opsForZSet().score(key,"张三"));
        System.out.println(redisTemplate.opsForZSet().reverseRank(key,"张三"));
        System.out.println(redisTemplate.opsForZSet().reverseRange(key,0,2));
   }

   @Test
    public void testKeys() {
        redisTemplate.delete("test:ids");
        System.out.println(redisTemplate.hasKey("test:ids"));
   }
    // 多次访问同一个key
    @Test
    public void testBoundOperations() {
        String redisKey = "test:count";
        BoundValueOperations operations = redisTemplate.boundValueOps(redisKey);
        // 个数 ++
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        operations.increment();
        // 访问数
        System.out.println(operations.get());
    }

    // 编程式 事务管理
    @Test
    public void testTransactional() {
        // 处理事务
        Object obj = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                // 在这方法内部演示 事务
                String key = "test:tx";
                // 启用事务
                redisOperations.multi();

                // 操作
                redisOperations.opsForSet().add(key,"张三");
                redisOperations.opsForSet().add(key,"李四");
                redisOperations.opsForSet().add(key,"王五");

                // 注意 此处 是在队列内部  在启用事务和提交事务之间 查询是无效的
                System.out.println("无效查询"+redisOperations.opsForSet().members(key));

                // 提交事务
                return redisOperations.exec();
            }
        });
        System.out.println(obj);
    }
}
