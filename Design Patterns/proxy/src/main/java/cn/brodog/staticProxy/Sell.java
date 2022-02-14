package cn.brodog.staticProxy;

/**
 * 销售接口
 * 定义一个销售接口，这个 厂家、各个地区的代理商 都实现这个接口
 * 茅台负责拿货给代理商，而代理商负责对公众进行售卖
 * @author By-Lin
 */
public interface Sell {
    /**
     * 去销售
     */
    void toSell();
}
