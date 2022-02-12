package cn.brodog.cor2.entity;

/**
 * 响应类
 * @author By-Lin
 */
public class Response {
    private String str;

    public Response(String str) {
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
        return "Response{" +
                "str='" + str + '\'' +
                '}';
    }
}
