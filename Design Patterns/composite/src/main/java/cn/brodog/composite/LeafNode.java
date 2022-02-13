package cn.brodog.composite;

/**
 * 叶子节点
 * @author By-Lin
 */
public class LeafNode extends Node{
    public String content;

    public LeafNode(String content) {
        this.content = content;
    }

    @Override
    public void show() {
        System.out.println(content);
    }
}
