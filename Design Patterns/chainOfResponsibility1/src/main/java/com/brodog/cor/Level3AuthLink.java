package com.brodog.cor;

import java.util.Objects;

/**
 * @author By-BroDog
 * @createTime 2023-01-25
 */
public class Level3AuthLink extends AuthLink{

    public Level3AuthLink(Integer levelUserId, String levelUserName) {
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
        // todo 三级鉴权操作
        AuthLink nextAuthLink = super.getNextAuthLink();
        System.out.println("=================   三级审批已开始  ================");
        if(Objects.isNull(nextAuthLink)) {
            System.out.println("=================   审批已结束  ================");
            return new AuthInfo(3, "HR", "审批完成");
        }
        System.out.println("=================   三级审批已完成  ================");
        return nextAuthLink.doAuth(levelUserId, levelUserName);
    }
}
