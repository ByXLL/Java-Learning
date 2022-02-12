package cn.brodog.cor;

/**
 * 消息实体
 * @author By-Lin
 */
public class Msg {
    private String msg;

    public Msg() {
    }

    public Msg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
