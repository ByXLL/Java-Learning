package cn.brodog.observer.v2;

import cn.brodog.observer.v2.entity.Baby;

/**
 * 观察者模式
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 这个时候我们想要对事件进行一个描述，是因为饿了哭，还是睡醒了哭，开始摔倒了哭
         * 然后观察者，去消费对应的事件，饿了喂奶，醒了抱抱.....
         */
        Baby baby = new Baby();
        baby.doCry("饿了");
    }
}
