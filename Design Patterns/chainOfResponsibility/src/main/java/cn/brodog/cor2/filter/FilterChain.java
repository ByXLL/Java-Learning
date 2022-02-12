package cn.brodog.cor2.filter;

import cn.brodog.cor2.entity.Request;
import cn.brodog.cor2.entity.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链 类
 * @author By-Lin
 */
public class FilterChain implements Filter {
     public List<Filter> filters = new ArrayList<Filter>();
    /**
     * 用于记录 FilterChain 过滤器链条上执行的 过滤器下标
     */
    private int index = 0;

    /**
     * 添加filter
     * @param filter    过滤器接口的实现类
     * @return FilterChain
     */
    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    /**
     * 实际上就是在递归这个
     * 而此时，我们定义的每一个的 filter 中 doFilter 返回的值，已经对当前链条的执行没有影响
     * 能控制的链条执行的就变成了当前方法的 返回值
     */
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        // 判断当前执行的 是否是最后一个 如果是则结束，不再执行后面的过滤
        if(index == filters.size()) { return false; }

        // 拿到当前要执行的过滤器
        Filter filter = filters.get(index);

        // 将指向下一个
        index++;

        return filter.doFilter(request, response, filterChain);
    }
}
