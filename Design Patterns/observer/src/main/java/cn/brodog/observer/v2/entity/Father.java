package cn.brodog.observer.v2.entity;

import cn.brodog.observer.v2.Observer;
import cn.brodog.observer.v2.event.Event;
import cn.brodog.observer.v2.event.WakeUpEvent;

/**
 * 爸爸
 * 实现 Observer 接口，实现对小孩子哭的监听
 * @author By-Lin
 */
public class Father implements Observer {
    private void doHug() {
        System.out.println("爸爸抱抱....");
    }

    private void doKiss() { System.out.println("爸爸亲亲......");}

    private void doNurse() {
        System.out.println("爸爸喂奶.......");
    }


    public void onEventTrigger(Event wakeUpEvent) {
        String reason = wakeUpEvent.getReason();
        if(reason.equals("醒了")) {
            doHug();
        }else if(reason.equals("饿了")) {
            doNurse();
        }else{
            doKiss();
        }
    }
}
