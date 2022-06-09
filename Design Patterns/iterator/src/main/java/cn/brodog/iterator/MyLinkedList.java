package cn.brodog.iterator;

/**
 * 链表 相比数组 不用考虑边界问题 可以实现动态扩展
 * @author By-Lin
 */
public class MyLinkedList implements MyCollection{
    Node head = null;
    Node tail = null;

    private int size = 0;

    public void add(Object o) {
        Node n = new Node(0);
        n.next = null;

        if(head == null) {
            head = n;
            tail = n;
        }
        tail.next = n;
        tail = n;
        size++;
    }

    /**
     * 是否存在下一个节点
     * @return true | false
     */
    public boolean hasNext() {
        return false;
    }

    /**
     * 匿名内部类
     */
    private class Node {
        // 真正的数据
        private Object o;
        // 下一个节点
        Node next;

        public Node(Object o) {
            this.o = o;
        }
    }

    public int size() {
        return size;
    }

    public MyIterator iterator() {
        return null;
    }

    private class LinkedListIterator implements MyIterator {

        /**
         * 是否存在下一个节点
         * @return true | false
         */
        public boolean hasNext() {
            return false;
        }

        /**
         * 下一个节点元素
         * @return 元素
         */
        public Object next() {
            return null;
        }
    }
}
