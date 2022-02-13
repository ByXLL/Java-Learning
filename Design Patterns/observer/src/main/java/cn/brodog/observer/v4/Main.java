package cn.brodog.observer.v4;

import cn.brodog.observer.v4.listener.ClickListener;
import cn.brodog.observer.v4.listener.TouchListener;

/**
 * 模拟一个按钮的事件监听
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) {
        Button button = new Button();
        button.addActionListener(new TouchListener())
                .addActionListener(new ClickListener());
        button.click();
    }
}
