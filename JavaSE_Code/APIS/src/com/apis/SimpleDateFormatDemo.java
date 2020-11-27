package com.apis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatDemo {
    public static void main(String[] args) throws ParseException {
        // 日期格式化
        // 从 Date 转 String
        Date date1 = new Date();
        // 传入格式 年 月 日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String timeStr = sdf.format(date1);

        System.out.println(timeStr);

        // 将 String 转成 Date
        String timeStr2 = "2020年11月10日 22:40:34";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"); // 这里这个格式需要跟你的日期格式对应 不然将转换失败
        Date date2 = sdf2.parse(timeStr2);

        System.out.println(date2);


        // 获取 时间戳
        long date3 = new Date().getTime();
        //方法 一
        //System.currentTimeMillis();
        ////方法 二
        //Calendar.getInstance().getTimeInMillis();
        System.out.println(date3);


        // 时间戳 转 年月日
        String dateStr = sdf.format(date3);
        System.out.println(dateStr);

        // str类型 转 需要将 String 转成 Long
        String timeStr3 = "1605088563265";
        long date4 = Long.valueOf(timeStr3);
        System.out.println(sdf.format(date4));
    }
}
