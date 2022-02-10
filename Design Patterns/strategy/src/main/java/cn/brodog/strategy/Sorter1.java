package cn.brodog.strategy;

import java.util.Comparator;

/**
 * 排序类
 * 使用泛型和比较器
 * @author By-Lin
 */
public class Sorter1<T> {

    /**
     * 通过泛型加自定义比较器的方式
     * 将比较器通过参数的形势传入，避免了在类中去实现 Comparable 接口，并且可以灵活的根据需求实时修改比较的逻辑，解耦
     * @param arr 泛型数组
     * @param comparator    泛型比较器
     */
    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length -1; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length; j++) {
                minPos = comparator.compare(arr[j],arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr,i,minPos);
        }
    }

    void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
