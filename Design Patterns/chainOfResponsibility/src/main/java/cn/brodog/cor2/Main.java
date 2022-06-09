package cn.brodog.cor2;

import cn.brodog.cor2.entity.Request;
import cn.brodog.cor2.entity.Response;
import cn.brodog.cor2.filter.CodeFilter;
import cn.brodog.cor2.filter.FilterChain;
import cn.brodog.cor2.filter.NumberFilter;
import cn.brodog.cor2.filter.PlusFilter;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 基于责任链模式 模拟http 过滤器
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 需求，基于.cor 包下的实现原理
         * 模拟一个http请求的request和response的过滤器
         * request 过滤器执行顺序 1->2->3
         * response 过滤器执行顺序 3->2->1
         */
        Request request = new Request("");
        Response response = new Response("");


        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new CodeFilter())
                .addFilter(new NumberFilter())
                .addFilter(new PlusFilter());

        // 这个时候的逻辑就是将触发执行的是链条队列中 doFilter() 方法，递归调用
        filterChain.doFilter(request,response,filterChain);

        System.out.println(request);
        System.out.println(response);

    }
}
