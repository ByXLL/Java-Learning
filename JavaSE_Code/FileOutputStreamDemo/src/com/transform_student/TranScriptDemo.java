package com.transform_student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/***
 *  将控制台输入的 成绩 保存至本地 成绩单
 */
public class TranScriptDemo {
    private static String PATH = "FileOutputStreamDemo\\dist\\files\\tranScript.txt";

    public static void main(String[] args) throws IOException {

        // 创建TreeSet集合  因为要通过总分排序 所以需要创建一个 匿名内部类 比较器
        TreeSet<TranScript> students = new TreeSet<TranScript>(new Comparator<TranScript>() {
            @Override
            public int compare(TranScript s1, TranScript s2) {
                int num = s2.getSum() - s1.getSum();
                // 次要条件
                int num2 = num==0 ? s2.getChinese() - s1.getChinese() : num;
                int num3 = num2==0 ? s2.getMath() - s1.getMath() : num2;
                int num4 = num3==0 ? s2.getEnglish() - s1.getEnglish() : num3;
                return num4;
            }
        });

        // 循环录入学生信息
        for (int i = 0; i < 5; i++) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入学生姓名：");
            String name = scanner.nextLine();
            System.out.print("请输入语文成绩：");
            int chinese = scanner.nextInt();
            System.out.print("请输入数学成绩：");
            int math = scanner.nextInt();
            System.out.print("请输入英语成绩：");
            int english = scanner.nextInt();

            TranScript student = new TranScript(name,chinese,math,english);
            students.add(student);
        }


        // 创建字符缓冲输出对象
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH));

        for (TranScript item : students) {
            String info = item.getName()+" " + item.getSum() +" ,"+item.getChinese()+" ,"+item.getMath()+" ,"+item.getEnglish();

            bufferedWriter.write(info);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

        bufferedWriter.close();
    }
}
