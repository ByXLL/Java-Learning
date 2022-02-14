package cn.brodog.staticProxy;

/**
 * 代理模式
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 场景一：模拟一个场景 茅台厂商 招收各个地区的代理商
         * 广西的、广东的，贵州的，代理商手上没有酒，消费者要买酒，只能通过代理商处下单，
         * 然后代理商，向茅台厂商进货，厂商卖给消费者，做了个中间人
         */
//        new MaotaiGxProxy(new Maotai()).toSell();


        /**
         * 场景二：这个时候，我们需要二级代理商，二级代理商（市级），只能去区级代理商哪里去拿货，而区级代理需要向厂商拿货
         * 按照原来的方式 想要代理，拿到销售权限（Maotai.toSell()），我们就得去代理 Maotai
         * 在代码上的体现就是 代理类的内部是 public Maotai maotai;  这样才能使用 Maotai.toSell()
         * 而这个时候，市级代理就违背了这样的原则，无法市级对区级进行代理
         * 这个时候 就需要将 MaotaiGxProxy 中的 public Maotai maotai改成  public Sell sell
         * Sell 接口内的 toSell方法
         */
        new MaotaiGxNnProxy(new MaotaiGxProxy(new Maotai())).toSell();
    }
}
