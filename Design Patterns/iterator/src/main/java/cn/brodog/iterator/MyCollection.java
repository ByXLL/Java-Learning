package cn.brodog.iterator;

/**
 * 自定义Collection接口
 * @author By-Lin
 */
public interface MyCollection {
    void add(Object o);
    int size();
    MyIterator iterator();
}
