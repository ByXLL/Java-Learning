package cn.brodog.observer.v3;

import cn.brodog.observer.v3.event.Event;

/**
 * 狗狗
 * 实现 Observer 接口，实现对小孩子哭的监听
 * @author By-Lin
 */
public class Dog implements Observer {
    private void doBark() {
        System.out.println("狗，汪汪汪.......");
    }

    /**
     * 当事件触发了
     * @param wakeUpEvent
     */
    public void onEventTrigger(Event wakeUpEvent) {
        doBark();
    }
}
