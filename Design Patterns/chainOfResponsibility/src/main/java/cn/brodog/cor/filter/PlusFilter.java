package cn.brodog.cor.filter;

import cn.brodog.cor.Msg;

/**
 * 加号过滤器
 * @author By-Lin
 */
public class PlusFilter implements Filter {
    public boolean doFilter(Msg msg) {
        String msgStr = msg.getMsg().replace("+","-");
        msg.setMsg(msgStr);
        return true;
    }
}
