package cn.brodog.observer.v1.entity;

import cn.brodog.observer.v1.Observer;

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

    public void doCry() {
        cry = true;

        // 触发每个观察者的具体监听方法
        for (Observer observer : observerList) {
            observer.onEventTrigger();
        }
    }
}
