package cn.brodog.composite;

/**
 * 组合模式 树状结构专用模式
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) {
        // 创建一个分支节点 用于做根
        BranchNode root = new BranchNode("根节点");
        // 创建两个分支节点 加到root下
        BranchNode leftChapter = new BranchNode("左边分支");
        BranchNode rightChapter = new BranchNode("右边分支");
        root.addNode(leftChapter);
        root.addNode(rightChapter);

        // 创建两个叶子节点 加到 左边分支 leftChapter 下
        Node lc1 = new LeafNode("左边分支---左边叶子");
        Node lc2 = new LeafNode("左边分支---右边叶子");
        leftChapter.addNode(lc1);
        leftChapter.addNode(lc2);

        // 创建两个叶子节点 加到 右边边分支 leftChapter 下
        Node rc1 = new LeafNode("右边分支---左边叶子");
        Node rc2 = new LeafNode("右边分支---右边叶子");
        rightChapter.addNode(rc1);
        rightChapter.addNode(rc2);

        showTree(root);

    }

    /**
     * 打印树
     * @param node
     */
    static void showTree(Node node) {
        node.show();

        // 如果是分支节点
        if(node instanceof BranchNode) {
            // 循环它下面的每一个分支
            for (Node itemNode : ((BranchNode) node).nodes) {
                // 递归调用
                showTree(itemNode);
            }
        }
    }
}
