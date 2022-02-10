package cn.brodog.strategy;

/**
 * 排序类
 * 当我们需要对不同类型进行排序的时候 会想到使用Object做为参数类型，但是Object 中没有 compareTo 方法
 * 这个时候我们将参数类型改成 Comparable[] ，在需要排序的类上去实现 Comparable 类，重写 compareTo 方法
 * @author By-Lin
 */
public class Sorter {
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length -1; i++) {
            int minPos = i;
            for (int j = i+1; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr,i,minPos);
        }
    }

    static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 传统写法
//    public static void sort(int[] arr) {
//        for (int i = 0; i < arr.length -1; i++) {
//            int minPos = i;
//            for (int j = i+1; j < arr.length; j++) {
//                minPos = arr[j] < arr[minPos] ? j : minPos;
//            }
//            swap(arr,i,minPos);
//        }
//    }
//
//    static void swap(int[] arr, int i, int j) {
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
//
//    // 扩展的比较类的大小
//    public static void sort(Cat[] arr) {
//        for (int i = 0; i < arr.length -1; i++) {
//            int minPos = i;
//            for (int j = i+1; j < arr.length; j++) {
//                // 2、使用该类声明的比较方法，进行比较
//                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
//            }
//            swap(arr,i,minPos);
//        }
//    }
//
//    static void swap(Cat[] arr, int i, int j) {
//        Cat temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//    }
}
