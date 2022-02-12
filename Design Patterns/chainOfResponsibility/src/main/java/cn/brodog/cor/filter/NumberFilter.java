package cn.brodog.cor.filter;

import cn.brodog.cor.Msg;

/**
 * 数字过滤器
 * @author By-Lin
 */
public class NumberFilter implements Filter {
    public boolean doFilter(Msg msg) {
        String msgStr = msg.getMsg().replace("0","6");
        msg.setMsg(msgStr);
//        return true;
        // 模拟当前过滤不通过
        return false;
    }
}
