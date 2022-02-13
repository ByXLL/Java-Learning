package cn.brodog.observer.v2.event;

/**
 * 醒了 事件
 * @author By-Lin
 */
public class WakeUpEvent extends Event {
    /**
     * 原因 / 标识
     */
    public String reason = "醒了";

    public WakeUpEvent() {
    }

    public WakeUpEvent(long timesTamp) {
        this.timesTamp = timesTamp;
    }
}
