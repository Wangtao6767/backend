package com.lensen.backend.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericDateFormat {

    SimpleDateFormat dateFormat;

    public GenericDateFormat(String pattern) {
        dateFormat = new SimpleDateFormat(pattern);
    }

    public String format(Date value) {
        return dateFormat.format(value);
    }

    public static String format(Date value, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(value);
    }

    public Date parse(String text) throws ParseException {
        Date result = null;
        try {
            result = dateFormat.parse(text);
        } catch (ParseException e) {
            result = DateParserUtils.getDate(text);
            if (result == null)
                throw e;
        }
        return result;
    }

    public static Date parse(String text, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(text);
    }

    public static Date strToDate(String strDate, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
        return date;
    }

    public static Date strToDateTime(String strDateTime, String fromat) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(fromat);
        Date dateTime = null;
        try {
            dateTime = dateTimeFormat.parse(strDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
        return dateTime;
    }

}
