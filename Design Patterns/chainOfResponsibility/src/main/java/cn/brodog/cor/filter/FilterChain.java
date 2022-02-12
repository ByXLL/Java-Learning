package cn.brodog.cor.filter;

import cn.brodog.cor.Msg;

import java.util.ArrayList;
import java.util.List;

/**
 * 过滤器链 类
 * 这里将当前类也去实现 Filter 类，这样就可以使用 addFilter() 将另外一个 FilterChain 链条给加进来
 * 这样就构成了一个完整的责任链
 * @author By-Lin
 */
public class FilterChain implements Filter {
     public List<Filter> filters = new ArrayList<Filter>();

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
     * 执行过滤器
     * 实现 Filter 中的doFilter方法
     * @param msg
     */
    public boolean doFilter(Msg msg) {
        for (Filter filter : filters) {
            // 这里去判断每一个filter过滤器的返回结果 判断是否继续执行后续的过滤
            if(!filter.doFilter(msg)) { return false; }
        }
        return true;
    }
}
