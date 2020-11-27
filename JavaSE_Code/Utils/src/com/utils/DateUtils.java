package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工具类
 * 构造方法私有
 * 成员方法静态
 */
public class DateUtils {
    public DateUtils() { }

    /**
     * 把日期格式 转换为指定格式的字符串
     * 返回值 String
     * 参数：Date date, String Format
     */
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String format1 = sdf.format(date);
        return format1;
    }

    /**
     * 把返回的字符串解析为指定格式的日期
     * 返回值类型： Date
     * 参数； String s, String format
     */
    public static Date stringToDate(String dateStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = sdf.parse(dateStr);
        return d;
    }
}
