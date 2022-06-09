package cn.brodog.iterator;

/**
 * iterator 迭代器模式
 * 实现功能
 * 1、构建动态扩展的容器
 * 2、数组的实现
 * 3、链表的实现
 * @author By-Lin
 */
public class Main {
    public static void main(String[] args) {
        /**
         * 现在有一个场景，就是我们使用的迭代器，怎么设计一个迭代器，保证可以针对不同的数据结构都可以实现遍历呢
         * 声明一个迭代器接口，在每一个容器内部提供一个根据自己数据结构特有的迭代器 然后通过这个迭代器实现遍历
         */
        MyCollection arrayList = new MyArrayList();
        for (int i = 0; i < 15; i++) {
            arrayList.add("List: " + i);
        }
        // 实现了自动扩容
        System.out.println(arrayList.size());


        // 迭代器遍历
        MyIterator arrayIterator = arrayList.iterator();
        while (arrayIterator.hasNext()) {
            System.out.println(arrayIterator.next());
        }


        MyCollection linkedList = new MyLinkedList();


    }
}
