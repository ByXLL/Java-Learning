package cn.brodog.cor2.filter;

import cn.brodog.cor2.entity.Request;
import cn.brodog.cor2.entity.Response;

/**
 * 过滤器接口
 * 定义一个方法 doFilter 让其实现类去实现对应的过滤逻辑，并且根据返回值确定是否继续执行后面的过滤
 * @author By-Lin
 */
public interface Filter {
    /**
     * 执行过滤操作
     * @param request       请求实体
     * @param response      响应实体
     * @param filterChain   过滤器链条
     * @return              是否继续执行过滤
     */
    boolean doFilter(Request request, Response response, FilterChain filterChain);
}
