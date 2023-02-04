package com.brodog.cor;

/**
 * 责任链审核抽象类
 * @author By-BroDog
 * @createTime 2023-01-22
 */
public abstract class AuthLink {
    protected Integer levelUserId;
    protected String levelUserName;


    /**
     * 核心属性 用于获取责任链条上的下一个
     */
    protected AuthLink nextAuthLink;

    public AuthLink(Integer levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink getNextAuthLink() {
        return nextAuthLink;
    }

    /**
     * 添加链条步骤
     * 此处返回this 用于可以使用链式一直调用 appendNext方法
     * @param nextAuthLink   需要添加的下一个节点
     * @return  返回当前链式节点对象
     */
    public AuthLink appendNext(AuthLink nextAuthLink) {
        this.nextAuthLink = nextAuthLink;
        return this;
    }

    /**
     * 抽象方法 执行需要执行的逻辑
     * @param levelUserId       serviceId
     * @param levelUserName     userName
     * @return 当前环节处理完成的结果信息
     */
    public abstract AuthInfo doAuth(Integer levelUserId, String levelUserName);
}
