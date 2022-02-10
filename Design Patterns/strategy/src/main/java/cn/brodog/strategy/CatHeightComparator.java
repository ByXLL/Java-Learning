package cn.brodog.strategy;

import java.util.Comparator;

/**
 * Cat 的 身高 Comparator 比较器
 * 实现 Comparator 接口，定义自己的比较逻辑
 * @author By-Lin
 */
public class CatHeightComparator implements Comparator<Cat> {
    /**
     * 根据业务定义自己的比较逻辑
     * 定义两个猫之间怎么比较大小  现在是根据高度
     * @param o1    需要比较的对象
     * @param o2    跟它比较的对象
     * @return       -1 比它小 1 比它大 0 相同
     */
    public int compare(Cat o1, Cat o2) {
        if(o1.getHeight() < o2.getHeight()) { return -1; }
        else if (o1.getHeight() > o2.getHeight()) { return  1; }
        else { return 0; }
    }
}
