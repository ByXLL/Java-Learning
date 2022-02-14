package cn.brodog.staticProxy;

/**
 * 茅台广西代理
 * 代理和代理对象都去实现 Sell 接口
 * 这里实现的 sell 接口
 * 相当于 消费者来到了代理商这里，而代理商手上没有酒，在这里去下了个单，要100瓶
 * 代理商 这个时候就需要厂商 给他批100瓶酒，再转给消费者
 * 这个时候，至始至终都是只有厂商生产了酒，卖的都是厂商的酒  （最终调用的是 Maotai.toSell() ）
 * @author By-Lin
 */
@SuppressWarnings("all")
public class MaotaiGxProxy implements Sell {
    // 场景一
//    public Maotai maotai;
//
//    public MaotaiGxProxy(Maotai maotai) {
//        this.maotai = maotai;
//    }

//    public void toSell() {
//        System.out.println("广西代理商拿货了.....");
//        maotai.toSell();
//    }

    // 场景二
    public Sell sell;

    public MaotaiGxProxy(Sell sell) {
        this.sell = sell;
    }

    public void toSell() {
        System.out.println("广西代理商拿货了.....");
        sell.toSell();
    }
}
