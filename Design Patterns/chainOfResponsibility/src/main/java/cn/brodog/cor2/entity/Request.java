package cn.brodog.cor2.entity;

/**
 * 请求类
 * @author By-Lin
 */
public class Request {
   private String str;

    public Request(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Request{" +
                "str='" + str + '\'' +
                '}';
    }
}
