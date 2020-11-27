package com.utils;

import java.text.ParseException;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) throws ParseException {
        Date d = new Date();

        String strDate = DateUtils.dateToString(d,"yyyy年MM月dd日 HH:mm:ss");
        System.out.println(strDate);


        String strDate2 = DateUtils.dateToString(d,"yyyy年MM月dd日");
        System.out.println(strDate2);

        String s = "2020年11月11日 21:47:31";
        Date dd = DateUtils.stringToDate(s,"yyyy年MM月dd日 HH:mm:ss");
        System.out.println(dd);
    }
}
