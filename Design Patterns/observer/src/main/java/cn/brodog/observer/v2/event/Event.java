package cn.brodog.observer.v2.event;

/**
 * 事件 父类
 * @author By-Lin
 */
public class Event {
    public long timesTamp;

    /**
     * 原因 / 标识
     */
    private String reason;

    public Event() {
    }

    public Event(long timesTamp, String reason) {
        this.timesTamp = timesTamp;
        this.reason = reason;
    }

    public long getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(long timesTamp) {
        this.timesTamp = timesTamp;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
