package com.mybatisplus.demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.demo.entity.User;
import com.mybatisplus.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    /**
     *  在我们添加的 UserMapper 接口 中继承了BaseMapper
     *  所有的简单的增删改查方法都来自父类，我们也可以去编写我们自己的扩展
     */
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void selectAll() {
        // 这里 参数 Wrapper 是一个构造器 可以构建对应的查询条件，不用可以 传入 null
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    void insertUser() {
        User user = new User();
        user.setName("狗哥");
        user.setAge(18);
        user.setEmail("123@163.com");

        int row = userMapper.insert(user);
        System.out.println(row);
        System.out.println(user);
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(1382520714299871233L);
        user.setAge(10);
        int row = userMapper.updateById(user);
        System.out.println(row);
        System.out.println(user);
    }

    // 模拟乐观锁
    @Test
    void testOptimisticLocker() {
        User user = userMapper.selectById(1);
        user.setAge(50);

        // 模拟第二个线程 抢先执行
        User user2 = userMapper.selectById(1);
        user2.setAge(11);
        userMapper.updateById(user2);

        userMapper.updateById(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    void testSelectByBatchId() {
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        userList.forEach(System.out::println);
    }

    @Test
    void testSelectByMap() {
        HashMap<String,Object> map = new HashMap<>();
        // 自定义查询条件
        map.put("name","狗哥");

        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    void testPage() {
        // 新建一个 page 对象 泛型传入的是对于的实体  参数是页面和页面大小
        Page<User> page = new Page<>(1,5);

        userMapper.selectPage(page,null);;

        List<User> records = page.getRecords();
        long total = page.getTotal();
        System.out.println(total);
        System.out.println(records);
    }

    @Test
    void testDelete() {
        int row = userMapper.deleteById(1000);
        System.out.println(row);
    }

    @Test
    void testDeleteBatchId() {
        int row = userMapper.deleteBatchIds(Arrays.asList(11111, 122211));
        System.out.println(row);
    }

    @Test
    void testDeleteByMap() {
        HashMap<String,Object> map = new HashMap<>();
        int row = userMapper.deleteByMap(map);
        System.out.println(row);
    }

    @Test
    void testLogicDelete() {
        int row = userMapper.deleteById(1L);
        System.out.println(row);
    }
}
