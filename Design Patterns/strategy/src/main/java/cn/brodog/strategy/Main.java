package cn.brodog.strategy;

import java.util.Arrays;

/**
 * 策略模式
 * 需求：编写一个排序类，排序的参数可能是 int数组、double数组、、、、、甚至是一个类数组
 * 按照以前的写法，就是在排序类上去重载 sort方法，但这不合实际
 *
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) {

//        int[] a = {9,1,3,8,7,2,5,4,6};
//        Sorter.sort(a);
//        System.out.println(Arrays.toString(a));


        Cat[] cats = {new Cat(1,1), new Cat(6,6),new Cat(5,5), new Cat(3,3)};
//        Sorter.sort(cats);
//        System.out.println(Arrays.toString(cats ));

        Sorter1<Cat> sorter1 = new Sorter1<Cat>();
        // 根据体重去比较
//        sorter1.sort(cats, new CatWeightComparator());
        // 根据身高去比较
        sorter1.sort(cats, new CatHeightComparator());
        /**
         * 这个时候就体现了 策略模式的灵活
         * 当我们想定义不一样的排序策略的时候，只需要去新建一个排序策略的比较器 Comparator
         * 并去实现 Comparator 接口 ，编写自己需要的比较策略
         */

        System.out.println(Arrays.toString(cats));

    }
}
