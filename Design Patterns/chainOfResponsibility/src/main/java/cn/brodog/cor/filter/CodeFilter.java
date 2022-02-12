package cn.brodog.cor.filter;

import cn.brodog.cor.Msg;

/**
 * 代码过滤器
 * @author By-Lin
 */
public class CodeFilter implements Filter {
    public boolean doFilter(Msg msg) {
        String msgStr = msg.getMsg().replace("<","[").replace(">","]");
        msg.setMsg(msgStr);
        return true;
    }
}
