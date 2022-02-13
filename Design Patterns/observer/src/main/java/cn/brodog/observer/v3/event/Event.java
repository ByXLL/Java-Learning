package cn.brodog.observer.v3.event;

/**
 * 事件 父类
 * 抽象类，定义一个getSource方法，用于获取源事件对象 / 宝宝
 * @author By-Lin
 */
public abstract class Event<T> {
    /**
     * 获取源事件 创建时间
     * @return  时间
     */
    public abstract long getTimesTamp();

    /**
     * 获取事件标识
     * @return  事件标识
     */
    public abstract String getReason();

    /**
     * 获取源事件对象
     * @return  事件源
     */
    public abstract T getSource();
}