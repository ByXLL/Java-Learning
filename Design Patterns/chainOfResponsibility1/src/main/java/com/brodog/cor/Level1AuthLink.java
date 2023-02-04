package com.brodog.cor;

import java.util.Objects;

/**
 * @author By-BroDog
 * @createTime 2023-01-25
 */
public class Level1AuthLink extends AuthLink{

    public Level1AuthLink(Integer levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    /**
     * 抽象方法 执行需要执行的逻辑
     *
     * @param levelUserId   serviceId
     * @param levelUserName userName
     * @return 当前环节处理完成的结果信息
     */
    @Override
    public AuthInfo doAuth(Integer levelUserId, String levelUserName) {
        // todo 一级鉴权操作
        System.out.println("=================   一级审批已开始  ================");
        AuthLink nextAuthLink = super.getNextAuthLink();
        if(Objects.isNull(nextAuthLink)) {
            System.out.println("=================   审批已结束  ================");
            return new AuthInfo(1, "项目PM", "审批通过");
        }
        System.out.println("=================   一级审批已完成  ================");
        return nextAuthLink.doAuth(levelUserId, levelUserName);
    }

}
