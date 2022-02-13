package cn.brodog.observer.v4.listener;

import cn.brodog.observer.v4.event.ActionEvent;

/**
 * 触摸事件监听器 实现 ActionListener 接口
 * @author By-Lin
 */
public class TouchListener implements ActionListener{
    /**
     * 事件监听者 处理事件行为
     * @param event 事件
     */
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        System.out.println("触发了触摸事件......" + source);
    }
}
