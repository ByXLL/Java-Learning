package cn.brodog.observer.v3;

import cn.brodog.observer.v3.event.Event;
import cn.brodog.observer.v3.event.HungryEvent;
import cn.brodog.observer.v3.event.WakeUpEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 宝宝
 * @author By-Lin
 */
public class Baby {
    /**
     * 是否在哭
     */
    private boolean cry = false;

    /**
     * 观察者列表
     */
    private List<Observer> observerList = new ArrayList<Observer>();

    // 默认初始化 添加几个观察者
    {
        observerList.add(new Father());
        observerList.add(new Mather());
        observerList.add(new Dog());
    }

    public boolean isCry() {
        return cry;
    }

    public void doCry(String reason) {
        cry = true;

        Event<Baby> event = null;

        // 当哭了，产生一个具体因为什么哭的事件
        if(reason.equals("醒了")) {
            System.out.println("宝宝醒了，哭了......");
            event = new WakeUpEvent(System.currentTimeMillis(),this);
        }else if(reason.equals("饿了")) {
            System.out.println("宝宝饿了，哭了......");
            event = new HungryEvent(System.currentTimeMillis(),this);
        }
        System.out.println("获取到了事件源对象" + event.getSource());

        // 将这个事件 传给所有的观察者 让他们去处理
        for (Observer observer : observerList) {
            observer.onEventTrigger(event);
        }
    }

    @Override
    public String toString() {
        return "Baby{" +
                "cry=" + cry +
                ", observerList=" + observerList +
                '}';
    }
}
