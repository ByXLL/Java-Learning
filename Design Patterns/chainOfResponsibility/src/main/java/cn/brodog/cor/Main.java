package cn.brodog.cor;

import cn.brodog.cor.filter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链模式
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 模拟需求， 前端回传的评论信息，做过滤，过滤代码、敏感词等、、、、
         */
        Msg msg = new Msg("++++，<p>6666666</p>,00000，你好");

        /**
         * 最原始的方式
         * 瓶颈，当需要添加新的过滤条件的时候，不容易扩展和代码过多
         */
//        String msgStr = msg.getMsg();
//        msgStr = msgStr.replace("+","-");
//        msgStr = msgStr.replace("<","[");
//        msgStr = msgStr.replace(">","]");
//        msgStr = msgStr.replace("0","6");
//        msg.setMsg(msgStr);
//        System.out.println(msg);


        /**
         * 1.0版本 使用过滤器写法
         * 将几个过滤器加到一起 这就是最简单的责任链模式 但还没有做成一个链条
         */
//        List<Filter> filters = new ArrayList<Filter>();
//        filters.add(new CodeFilter());
//        filters.add(new NumberFilter());
//        filters.add(new PlusFilter());
//
//        for (Filter filter : filters) {
//            filter.doFilter(msg);
//        }
//        System.out.println(msg);


        /**
         * 2.0版本 使用链式方式，将两个链条结婚在一起，构成责任链
         * 并且通过判断 每一个filter执行后的返回值 来判断是否执行后续的过滤
         */
        FilterChain filterChain1 = new FilterChain();
        // 模拟NumberFilter 过滤不通过
        filterChain1.addFilter(new PlusFilter())
                .addFilter(new NumberFilter());

        // 这时候我们去新建另外一个链条
        FilterChain filterChain2 = new FilterChain();
        filterChain2.addFilter(new CodeFilter());

        //将这个链条加到前面这个链条上
        filterChain1.addFilter(filterChain2);

        // 就可以省略 使用第二个链条再去执行一遍过滤
        // filterChain2.doFilter(msg);

        // 由于第一个链条的最后一个过滤不通过，此时第二个链条的过滤就不会执行
        filterChain1.doFilter(msg);
        System.out.println(msg);


    }
}
