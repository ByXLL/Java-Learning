package cn.brodog.staticProxy;

/**
 * 茅台广西代理 -> 南宁市代理
 * 代理和代理对象都去实现 Sell 接口
 * @author By-Lin
 */
public class MaotaiGxNnProxy implements Sell {
    public Sell sell;

    public MaotaiGxNnProxy(Sell sell) {
        this.sell = sell;
    }

    public void toSell() {
        System.out.println("南宁代理商拿货了.....");
        sell.toSell();
    }
}
