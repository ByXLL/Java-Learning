package cn.brodog.observer.v1;

import cn.brodog.observer.v1.entity.Baby;

/**
 * 观察者模式
 * @author By-Lin
 */
@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        /**
         * 模拟一个场景，一个小孩，当小孩哭的时候，爸爸饱，妈妈喂奶，狗狗叫
         * 这个是时候就是一个简单的一个观察者模式
         */
        Baby baby = new Baby();
        baby.doCry();

    }
}
