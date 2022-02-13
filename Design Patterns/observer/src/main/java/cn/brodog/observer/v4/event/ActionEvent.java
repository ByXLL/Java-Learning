package cn.brodog.observer.v4.event;

/**
 * 事件类
 * @author By-Lin
 */
public class ActionEvent {
    /**
     * 事件创建时间
     */
    private long timesTamp;

    /**
     * 事件源对象
     */
    private Object source;

    public ActionEvent(long timesTamp, Object source) {
        this.timesTamp = timesTamp;
        this.source = source;
    }

    public long getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(long timesTamp) {
        this.timesTamp = timesTamp;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "ActionEvent{" +
                "timesTamp=" + timesTamp +
                ", source=" + source +
                '}';
    }
}
