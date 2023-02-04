package cn.brodog.strategy;

import java.util.Comparator;

/**
 * 人身高比较器
 * @author By-BroDog
 * @createTime 2023-01-21
 */
public class ManHeightComparator implements Comparator<Man> {

    public int compare(Man o1, Man o2) {
        if(o1.getHeight() > o2.getHeight()) { return -1; }
        else if(o1.getHeight() < o2.getHeight() ) { return 1; }
        return 0;
    }
}
