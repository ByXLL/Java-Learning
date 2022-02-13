package cn.brodog.observer.v4.listener;

import cn.brodog.observer.v4.event.ActionEvent;

/**
 * 事件监听者 接口  声明事件监听者的默认实现
 * 实现类需要实现 actionPerformed 方法
 * @author By-Lin
 */
public interface ActionListener {
    /**
     * 事件监听者 处理事件行为
     * @param event     事件
     */
    void actionPerformed(ActionEvent event);
}
