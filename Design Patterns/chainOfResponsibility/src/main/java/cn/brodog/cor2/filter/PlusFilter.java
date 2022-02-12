package cn.brodog.cor2.filter;

import cn.brodog.cor2.entity.Request;
import cn.brodog.cor2.entity.Response;

/**
 * 加号过滤器
 * @author By-Lin
 */
public class PlusFilter implements Filter {
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        String reqStr = request.getStr();
        reqStr += "---请求执行了加号过滤---";
        request.setStr(reqStr);

        filterChain.doFilter(request,response,filterChain);

        String resStr = response.getStr();
        resStr += "---响应执行了加号过滤---";
        response.setStr(resStr);
        return true;
    }
}
