/*
 * Copyright (C), 2015-2017, 汇银乐虎电子商务有限公司
 * FileName: Convert.java
 * Author:   lumb
 * Date:     2017-3-30 16:32:30
 * Description: //模块目的、功能描述
 * History: //修改记录
 * &lt;author&gt;      &lt;time&gt;      &lt;version&gt;    &lt;desc&gt;
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * The Class Convert.
 */
public class Convert {

    /**
     * Str to int.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the int
     */
    public static int strToInt(String str, int defaultValue) {
        int Result = defaultValue;

        try {
            Result = Integer.parseInt(str);
        } catch (Exception arg3) {
            ;
        }

        return Result;
    }

    /**
     * Str to long.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the long
     */
    public static long strToLong(String str, long defaultValue) {
        long Result = defaultValue;

        try {
            Result = Long.parseLong(str);
        } catch (Exception arg5) {
            ;
        }

        return Result;
    }

    /**
     * Str to float.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the float
     */
    public static float strToFloat(String str, float defaultValue) {
        float Result = defaultValue;

        try {
            Result = Float.parseFloat(str);
        } catch (Exception arg3) {
            ;
        }

        return Result;
    }

    /**
     * Str to double.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the double
     */
    public static double strToDouble(String str, double defaultValue) {
        double Result = defaultValue;

        try {
            Result = Double.parseDouble(str);
        } catch (Exception arg5) {
            ;
        }

        return Result;
    }

    /**
     * Str to boolean.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return true, if successful
     */
    public static boolean strToBoolean(String str, boolean defaultValue) {
        boolean Result = defaultValue;

        try {
            Result = Boolean.parseBoolean(str);
        } catch (Exception arg3) {
            ;
        }

        return Result;
    }

    /**
     * Str to date.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the date
     */
    public static Date strToDate(String str, Date defaultValue) {
        return strToDate(str, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * Str to date.
     *
     * @param str          the str
     * @param format       the format
     * @param defaultValue the default value
     * @return the date
     */
    public static Date strToDate(String str, String format, Date defaultValue) {
        Date Result = defaultValue;
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);

        try {
            Result = formatter.parse(str);
        } catch (Exception arg5) {
            ;
        }

        return Result;
    }

    /**
     * Date to str.
     *
     * @param date         the date
     * @param defaultValue the default value
     * @return the string
     */
    public static String dateToStr(Date date, String defaultValue) {
        return dateToStr(date, "yyyy-MM-dd HH:mm:ss", defaultValue);
    }

    /**
     * Date to str.
     *
     * @param date         the date
     * @param format       the format
     * @param defaultValue the default value
     * @return the string
     */
    public static String dateToStr(Date date, String format, String defaultValue) {
        String Result = defaultValue;
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.ENGLISH);

        try {
            Result = formatter.format(date);
        } catch (Exception arg5) {
            ;
        }

        return Result;
    }

    /**
     * Str to str.
     *
     * @param str          the str
     * @param defaultValue the default value
     * @return the string
     */
    public static String strToStr(String str, String defaultValue) {
        String Result = defaultValue;
        if (str != null && !str.isEmpty()) {
            Result = str;
        }

        return Result;
    }

    /**
     * Date to sql date.
     *
     * @param date the date
     * @return the java.sql. date
     */
    public static java.sql.Date dateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Sql date to date.
     *
     * @param date the date
     * @return the date
     */
    public static Date sqlDateToDate(java.sql.Date date) {
        return new Date(date.getTime());
    }

    /**
     * Date to sql timestamp.
     *
     * @param date the date
     * @return the timestamp
     */
    public static Timestamp dateToSqlTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    /**
     * Ql timestamp to date.
     *
     * @param date the date
     * @return the date
     */
    public static Date qlTimestampToDate(Timestamp date) {
        return new Date(date.getTime());
    }

    /**
     * Strto asc.
     *
     * @param st the st
     * @return the int
     */
    public static int strtoAsc(String st) {
        byte[] gc = st.getBytes();
        return gc[0];
    }

    /**
     * Int to char.
     *
     * @param backNum the backNum
     * @return the char
     */
    public static char intToChar(int backNum) {
        return (char) backNum;
    }

    /**
     * 字符串转换为Byte
     *
     * @return
     */
    public static Byte strToByte(String val) {
        try {
            return Byte.valueOf(val);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 字符串转换为date
     *
     * @param dateString
     * @return
     */
    public static Date StringToDate(String dateString) {
        try {
            if (StringUtils.isEmpty(dateString)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateString);
        } catch (Exception e) {
            return null;
        }
    }

}
