package cn.brodog.cor.filter;

import cn.brodog.cor.Msg;

/**
 * 过滤器接口
 * 定义一个方法 doFilter 让其实现类去实现对应的过滤逻辑，并且根据返回值确定是否继续执行后面的过滤
 * @author By-Lin
 */
public interface Filter {
    /**
     * 过滤
     * @param msg
     * @return 是否继续执行过滤
     */
    boolean doFilter(Msg msg);
}
