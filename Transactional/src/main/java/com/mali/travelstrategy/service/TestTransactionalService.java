package com.mali.travelstrategy.service;

/**
 * 测试事务 service
 * @author By-Lin
 */
public interface TestTransactionalService {

    /**
     * 测试 批量新增事务
     */
    void testBatchInsert();

    /**
     * 测试 保存单个流程数据
     */
    void startOneProcess(int i);
}
