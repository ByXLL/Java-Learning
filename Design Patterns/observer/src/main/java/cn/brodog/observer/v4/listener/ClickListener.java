package cn.brodog.observer.v4.listener;

import cn.brodog.observer.v4.Button;
import cn.brodog.observer.v4.event.ActionEvent;

/**
 * 点击事件监听器 实现 ActionListener 接口
 * @author By-Lin
 */
public class ClickListener implements ActionListener{
    /**
     * 事件监听者 处理事件行为
     * @param event 事件
     */
    public void actionPerformed(ActionEvent event) {
        Button source = (Button) event.getSource();
        System.out.println("触发了点击事件......" + source.getColor());
    }
}
