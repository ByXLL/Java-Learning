package cn.brodog.observer.v3;

import cn.brodog.observer.v3.event.Event;

/**
 * 妈妈
 * 实现 Observer 接口，实现对小孩子哭的监听
 * @author By-Lin
 */
public class Mather implements Observer {

    private void doNurse() {
        System.out.println("妈妈喂奶.......");
    }

    private void doHug() {
        System.out.println("妈妈抱抱....");
    }

    private void doKiss() { System.out.println("妈妈亲亲......");}


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
