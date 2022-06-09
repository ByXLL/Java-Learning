package cn.brodog.iterator;

/**
 * 自定义迭代器接口
 * 实现不同的数据结构都可以实现遍历
 * 在每一个实现类中实现以下方法
 * @author By-Lin
 */
public interface MyIterator {
    /**
     * 是否存在下一个节点
     * @return true | false
     */
    boolean hasNext();

    /**
     * 下一个节点元素
     * @return 元素
     */
    Object next();
}
