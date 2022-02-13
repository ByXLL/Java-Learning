package cn.brodog.observer.v4;

import cn.brodog.observer.v4.event.ActionEvent;
import cn.brodog.observer.v4.listener.ActionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 按钮类
 * @author By-Lin
 */
public class Button {
    /**
     * 模拟获取事件源对象的数据
     */
    private String color = "red";

    /**
     * 按钮的 事件监听器
     */
    private List<ActionListener> actionListeners = new ArrayList<ActionListener>();

    /**
     * 添加事件监听器
     * @param actionListener        事件监听器
     */
    public Button addActionListener(ActionListener actionListener) {
        actionListeners.add(actionListener);
        return this;
    }

    /**
     * 按钮点击
     */
    public void click() {
        // 当点击的时候，就会去新建一个事件,
        ActionEvent actionEvent = new ActionEvent(System.currentTimeMillis(),this);

        // 拿出它所有的监听者，触发监听者上的方法，并将当前产生的事件传给事件监听者
        for (ActionListener listener : actionListeners) {
            listener.actionPerformed(actionEvent);
        }
    }

    public String getColor() {
        return color;
    }
}
