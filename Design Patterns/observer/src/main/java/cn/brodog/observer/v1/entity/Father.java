package cn.brodog.observer.v1.entity;

import cn.brodog.observer.v1.Observer;

/**
 * 爸爸
 * 实现 Observer 接口，实现对小孩子哭的监听
 * @author By-Lin
 */
public class Father implements Observer {
    /**
     * 当孩子哭的时候，父亲执行的动作
     */
    private void doHug() {
        System.out.println("爸爸抱抱....");
    }

    /**
     * 当事件触发了
     */
    public void onEventTrigger() {
        doHug();
    }
}
