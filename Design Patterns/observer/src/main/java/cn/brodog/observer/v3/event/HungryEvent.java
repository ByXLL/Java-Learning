package cn.brodog.observer.v3.event;


import cn.brodog.observer.v3.Baby;

/**
 * 饿了 事件
 * @author By-Lin
 */
public class HungryEvent extends Event<Baby> {
    private long timesTamp;
    private String reason = "饿了";
    private Baby source;

    public HungryEvent(long timesTamp, Baby source) {
        this.timesTamp = timesTamp;
        this.source = source;
    }

    @Override
    public long getTimesTamp() {
        return timesTamp;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public Baby getSource() {
        return source;
    }
}
