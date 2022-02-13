package cn.brodog.observer.v1.entity;

import cn.brodog.observer.v1.Observer;

/**
 * 妈妈
 * 实现 Observer 接口，实现对小孩子哭的监听
 * @author By-Lin
 */
public class Mather implements Observer {
    private void doNurse() {
        System.out.println("妈妈喂奶.......");
    }

    /**
     * 当事件触发了
     */
    public void onEventTrigger() {
        doNurse();
    }
}
