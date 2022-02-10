package cn.brodog.strategy;

/**
 * 猫类
 * 实现 Comparable 类，重写 compareTo 方法 用于将当前类变成 Comparable 的接口实现类，
 * 这样就可以使用我们定义的 Sort类
 * @author By-Lin
 */
public class Cat implements Comparable<Cat>{
    private int weight;
    private int height;

    public Cat(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }




//    /**
//     * 比较方法
//     * 1、定义两个猫之间怎么比较大小
//     * @param c     比较的对象
//     * @return      -1 比它小 1 比它大 0 相同
//     */
//    public int compareTo(Cat c) {
//        if(this.weight < c.weight) { return -1; }
//        else if (this.weight > c.weight) { return  1; }
//        else { return 0; }
//    }

    @Override
    public String toString() {
        return "Cat{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

    /**
     * 比较方法
     * 1、定义两个猫之间怎么比较大小
     * @param c     比较的对象
     * @return      -1 比它小 1 比它大 0 相同
     */
    public int compareTo(Cat c) {
        if(this.weight < c.weight) { return -1; }
        else if (this.weight > c.weight) { return  1; }
        else { return 0; }
    }







    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
