package cn.brodog.observer.v2;

import cn.brodog.observer.v2.event.Event;
import cn.brodog.observer.v2.event.WakeUpEvent;

/**
 * 观察者接口
 * 声明一个方法，让实现类实现该方法，在此方法内，去触发具体的观察者的所具有的方法
 * 如：妈妈喂奶，爸爸抱
 * @author By-Lin
 */
public interface Observer {
    /**
     * 当事件触发了
     * 观察者观察具体的事件，将这个事件本身给传回去，这样就记录了当前的事件是原始状态
     * @param wakeUpEvent
     */
    void onEventTrigger(Event wakeUpEvent);
}
