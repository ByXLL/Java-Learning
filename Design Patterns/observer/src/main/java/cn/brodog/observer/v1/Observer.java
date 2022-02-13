package cn.brodog.observer.v1;

/**
 * 观察者接口
 * 声明一个方法，让实现类实现该方法，在此方法内，去触发具体的观察者的所具有的方法
 * 如：妈妈喂奶，爸爸抱
 * @author By-Lin
 */
public interface Observer {
    /**
     * 当事件触发了
     */
    void onEventTrigger();
}
