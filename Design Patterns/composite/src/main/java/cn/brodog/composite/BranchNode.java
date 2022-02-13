package cn.brodog.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 分支节点
 * @author By-Lin
 */
public class BranchNode extends Node{
    /** 分支名称 **/
    public String name;

    /** 当前分支下的节点 **/
    public List<Node> nodes = new ArrayList<Node>();

    public BranchNode(String name) {
        this.name = name;
    }

    /**
     * 添加节点
     * @param node      节点
     */
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void show() {
        System.out.println(name);
    }
}
