package cn.brodog.iterator;

/**
 * 数组容器，不考虑边界问题，可以实现动态扩展
 * @author By-Lin
 */
public class MyArrayList implements MyCollection{
    Object[] objects = new Object[10];
    private int index = 0;

    /**
     * 添加元素
     * @param o
     */
    public void add(Object o) {
        if(index == objects.length) {
            // 扩容
            Object[] newObjects = new Object[objects.length * 2 ];
            System.arraycopy(objects,0,newObjects,0,objects.length);
            objects = newObjects;
        }
        objects[index] = o;
        index++;
    }

    public int size() {
        return objects.length;
    }

    /**
     * 返回一个当前数据结构特有的迭代器
     * @return 迭代器
     */
    public MyIterator iterator() {
        return new ArrayListIterator();
    }

    /**
     * 内部类 提供特有的迭代器
     */
    private class ArrayListIterator implements MyIterator {
        /**
         * 当前访问到的下标
         */
        private int currentIndex = 0;

        /**
         * 是否存在下一个节点
         * @return true | false
         */
        public boolean hasNext() {
            return currentIndex < index;
        }

        /**
         * 下一个节点元素
         * @return 元素
         */
        public Object next() {
            Object o = objects[currentIndex];
            currentIndex++;
            return o;
        }

    }
}
