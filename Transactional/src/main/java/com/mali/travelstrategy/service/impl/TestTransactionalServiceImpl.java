package com.mali.travelstrategy.service.impl;

import com.mali.travelstrategy.entity.Raiders;
import com.mali.travelstrategy.entity.RaidersStat;
import com.mali.travelstrategy.entity.User;
import com.mali.travelstrategy.exception.LoginException;
import com.mali.travelstrategy.mapper.*;
import com.mali.travelstrategy.service.TestTransactionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author By-Lin
 */
@Service
public class TestTransactionalServiceImpl implements TestTransactionalService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RaidersMapper raidersMapper;

    @Autowired
    private AttractionsMapper attractionsMapper;

    @Autowired
    private RaidersCommentMapper raidersCommentMapper;

    @Autowired
    private RaidersStatMapper raidersStatMapper;

    @Autowired
    private RaidersServiceImpl raidersService;

    @Autowired
    private RaidersStatServiceImpl raidersStatService;

    /**
     * 测试 批量新增事务
     */
    @Override
    public void testBatchInsert() {
        int total = 51;
        int successTotal = 0;
        int failTotal = 0;
        for (int i = 0; i < total; i++) {
            System.out.println("----正在执行第 " + i+1 + " 次操作");
            try {
                startOneProcess(i+1);
                successTotal++;
            }catch (Exception e) {
                failTotal++;
                System.out.println("----第 " + i+1 + " 次操作,操作失败");
            }
        }
        System.out.println("执行成功个数：" + successTotal +" 执行失败个数：" + failTotal);
    }

    @Transactional(rollbackFor = Exception.class)
    public void testChildTransaction() {
        Raiders raiders = getRaiders(1);
        raidersService.add(raiders);

        RaidersStat raidersStat = getRaidersStat(1);
        // 当使用try catch 的时候 且子事务 propagation 为 REQUIRES_NEW 时 外层事务不会受到内部异常问题
        // 当使用try catch 的时候 且子事务 propagation 为 默认时 将会抛出异常 Transaction rolled back because it has been marked as rollback-only

        try {
            raidersStatService.add(raidersStat);
        }catch (Exception e) {
            System.out.println("子事务异常捕捉");
        }
        System.out.println("完整事务执行完成 -----");
    }








    @Override
    @Transactional(rollbackFor = Exception.class)
    public void startOneProcess(int i) {
        insertOneUser(i);
        if(i>20) {
            throw new LoginException("手动抛出异常");
        }
        insertOneRaiders(i);
    }

    private RaidersStat getRaidersStat(int i) {
        RaidersStat raidersStat = new RaidersStat();
        raidersStat.setRaidersId(i);
        raidersStat.setUserId(1);
        return raidersStat;
    }

    private void insertOneUser(int i) {
        User user = getUser(i);
        userMapper.insert(user);
    }

    private void insertOneRaiders(int i) {
        Raiders raiders = getRaiders(i);
        raidersMapper.insert(raiders);
    }


    private User getUser(int i) {
        User user = new User();
        user.setUserName("用户名-" + i);
        user.setNickName("昵称-" + i);
        return user;
    }


    private List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            User user = new User();
            user.setUserName("用户名-" + i);
            user.setNickName("昵称-" + i);
            userList.add(user);
        }
        return userList;
    }


    private Raiders getRaiders(int i) {
        Raiders raiders = new Raiders();
        raiders.setTitle("标题-" + i);
        raiders.setContent("正文-" + i);
        return raiders;
    }

    private List<Raiders> getRaiderList() {
        List<Raiders> raidersList = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Raiders raiders = new Raiders();
            raiders.setTitle("标题-" + i);
            raiders.setContent("正文-" + i);
            raidersList.add(raiders);
        }
        return raidersList;
    }
}
