package cn.brodog.observer.v2.event;

/**
 * 饿了 事件
 * @author By-Lin
 */
public class HungryEvent extends Event{
    /**
     * 原因 / 标识
     */
    public String reason = "饿了";

    public HungryEvent() {
    }

    public HungryEvent(long timesTamp) {
        this.timesTamp = timesTamp;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }
}
