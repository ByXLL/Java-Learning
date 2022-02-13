package cn.brodog.observer.v3.event;

import cn.brodog.observer.v3.Baby;

/**
 * 醒了 事件
 * @author By-Lin
 */
public class WakeUpEvent extends Event<Baby> {
    private long timesTamp;
    private String reason = "醒了";
    private Baby source;

    public WakeUpEvent(long timesTamp, Baby source) {
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
