//package com.mali.travelstrategy.config;
//
//import com.baomidou.mybatisplus.core.injector.AbstractMethod;
//import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
//import com.mali.travelstrategy.method.UpdateBatchMethod;
//
//import java.util.List;
//
///**
// * @author By-Lin
// */
//public class CustomizedSqlInjector extends DefaultSqlInjector {
//    /**
//     * 如果只需增加方法，保留mybatis plus自带方法，
//     * 可以先获取super.getMethodList()，再添加add
//     */
//    @Override
//    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
//        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
//        methodList.add(new UpdateBatchMethod());
//        return methodList;
//    }
//}