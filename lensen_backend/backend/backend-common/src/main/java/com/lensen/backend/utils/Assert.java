package com.lensen.backend.utils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Assert {
    public static boolean isEmptyString(String string) {
        if (null == string || "".equals(string.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyString(String string) {
        return !isEmptyString(string);
    }

    public static boolean isEmptyList(List list) {
        if (null == list || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyList(List list) {
        return !isEmptyList(list);
    }

    public static boolean isEmptyMap(Map map) {
        if (null == map || map.size() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmptyMap(Map map) {
        return !isEmptyMap(map);
    }

    // 2个float是否相等。
    public static boolean equalsFloat(Float floatA, Float floatB) {
        if (null == floatA || null == floatB) {
            return false;
        }
        if (Math.abs(floatA - floatB) < 0.01) {
            return true;
        }
        return false;
    }

    // 2个Double是否相等。
    public static boolean equalsDouble(Double doubleA, Double doubleB) {
        if (null == doubleA || null == doubleB) {
            return false;
        }
        if (Math.abs(doubleA - doubleB) < 0.01) {
            return true;
        }
        return false;
    }

    // 某日期是否在日期区间内。
    public static boolean betweenDate(String dateStr, String beginDateStr,
                                      String endDateStr) {
        if (dateStr.compareTo(beginDateStr) >= 0
                && dateStr.compareTo(endDateStr) <= 0) {
            return true;
        }
        return false;
    }

    // 手机号是否合法
    public static boolean isValidMobile(String mobile) {
        if (isEmptyString(mobile)) {
            return false;
        }
        String str = "^((13|15|18|14|17)+\\d{9})$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    // 邮箱是否合法
    public static boolean isValidEmail(String email) {
        if (isEmptyString(email)) {
            return false;
        }
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        return matcher.matches();
    }

    // 2个时间之间的差值
    public static Integer minutesBetween(Date time1, Date time2) {
        if (null == time1 || null == time2) {
            return null;
        }
        long msec = time2.getTime() - time1.getTime();
        long min = (msec / (60 * 1000));
        return (int) Math.floor(min);
    }

    //2个时间之间的差值,为0进一
    public static Integer minutesBetweenNew(Date time1, Date time2) {
        if (null == time1 || null == time2) {
            return 0;
        }
        long msec = time2.getTime() - time1.getTime();
        long min = (msec / (60 * 1000));
        if (min == 0) {
            min += 1;
        }
        return (int) Math.floor(min);
    }

    // 固定电话是否合法
    public static boolean isValidPhone(String phone) {
        if (isEmptyString(phone)) {
            return false;
        }
        String str = "^(0(10|2[1-3]|[3-9]\\d{2}))?[1-9]\\d{6,7}$";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
