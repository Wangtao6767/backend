package com.lensen.backend.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public final class DateParserUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateParserUtils.class);

    private DateParserUtils() {
    }

    private static SimpleDateFormat[] dateFormats = null;

    static {
        final String[] possibleDateFormats = {"yyyy-MM-dd", "yyyy/MM/dd",
                "EEE, dd MMM yyyy HH:mm:ss z", "EEE,dd MMM yyyy HH:mm:ss z",
                "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:sszzzz",
                "yyyy-MM-dd'T'HH:mm:ss z", "yyyy-MM-dd'T'HH:mm:ssz",
                "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd'T'HHmmss.SSSz",
                "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm:ss"};

        dateFormats = new SimpleDateFormat[possibleDateFormats.length];
        TimeZone gmtTZ = TimeZone.getTimeZone("GMT+8:00");
        for (int i = 0; i < possibleDateFormats.length; i++) {
            dateFormats[i] = new SimpleDateFormat(possibleDateFormats[i],
                    Locale.ENGLISH);
            dateFormats[i].setTimeZone(gmtTZ);
        }

    }

    // Mon, 07 Oct 2002 03:16:15 GMT
    private static SimpleDateFormat dfA = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);

    // 2002-09-19T02:51:16+0200
    private static SimpleDateFormat dfB = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ssZ");

    // 2002-09-19T02:51:16
    private static SimpleDateFormat dfC = new SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss");

    // 2002-09-19
    private static SimpleDateFormat dfD = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDate(String strdate) {
        Date result = null;
        strdate = StringUtils.trimToNull(strdate);
        if (strdate == null) {
            return result;
        }
        if (strdate.length() > 10) {

            // TODO deal with +4:00 (no zero before hour)
            if ((strdate.substring(strdate.length() - 5).indexOf("+") == 0 || strdate
                    .substring(strdate.length() - 5).indexOf("-") == 0)
                    && strdate.substring(strdate.length() - 5).indexOf(":") == 2) {

                String sign = strdate.substring(strdate.length() - 5, strdate
                        .length() - 4);

                strdate = strdate.substring(0, strdate.length() - 5) + sign
                        + "0" + strdate.substring(strdate.length() - 4);
                // logger.debug("CASE1 : new date " + strdate + " ? "
                // + strdate.substring(0, strdate.length() - 5));

            }

            String dateEnd = strdate.substring(strdate.length() - 6);

            // try to deal with -05:00 or +02:00 at end of date
            // replace with -0500 or +0200
            if ((dateEnd.indexOf("-") == 0 || dateEnd.indexOf("+") == 0)
                    && dateEnd.indexOf(":") == 3) {
                if ("GMT".equals(strdate.substring(strdate.length() - 9,
                        strdate.length() - 6))) {
                    LOGGER.debug("General time zone with offset, no change ");
                } else {
                    // continue treatment
                    String oldDate = strdate;
                    String newEnd = dateEnd.substring(0, 3)
                            + dateEnd.substring(4);
                    strdate = oldDate.substring(0, oldDate.length() - 6)
                            + newEnd;
                }
            }
        }
        int i = 0;
        while (i < dateFormats.length) {
            try {
                result = dateFormats[i].parse(strdate);
                break;
            } catch (java.text.ParseException eA) {
                String s = "parsing " + strdate + " ["
                        + dateFormats[i].toPattern()
                        + "] without success, trying again.";
                // System.out.println(s);
                LOGGER.debug(s);
                i++;
            }
        }

        return result;
    }

    /**
     * Tries different date formats to parse against the given string
     * representation to retrieve a valid Date object.
     */
    public static Date getDateOLD(String strdate) {
        Date result = null;

        try {
            result = dfA.parse(strdate);
        } catch (java.text.ParseException eA) {
            LOGGER.warn("Error parsing date (A): " + eA.getMessage());
            try {
                result = dfB.parse(strdate);
            } catch (java.text.ParseException eB) {
                LOGGER.warn("Error parsing date (B): " + eB.getMessage());
                try {
                    result = dfC.parse(strdate);
                    // try to retrieve the timezone anyway
                    result = extractTimeZone(strdate, result);
                } catch (java.text.ParseException eC) {
                    LOGGER.warn("Error parsing date (C): " + eC.getMessage());
                    try {
                        result = dfD.parse(strdate);
                    } catch (java.text.ParseException eD) {
                        LOGGER.warn("Error parsing date (D): " + eD.getMessage());
                        eD.printStackTrace();
                    }
                }
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Parsing date '" + strdate + "' resulted in: " + result);
        }
        if (result == null) {
            LOGGER.warn("No appropiate date could be extracted from " + strdate);

        }
        return result;
    }

    private static Date extractTimeZone(String strdate, Date thedate) {
        // try to extract -06:00
        String tzSign = strdate.substring(strdate.length() - 6, strdate
                .length() - 5);
        String tzHour = strdate.substring(strdate.length() - 5, strdate
                .length() - 3);
        String tzMin = strdate.substring(strdate.length() - 2);
        if (tzSign.equals("-") || tzSign.equals("+")) {
            int h = Integer.parseInt(tzHour);
            int m = Integer.parseInt(tzMin);
            // NOTE: this is really plus, since perspective is from GMT
            if (tzSign.equals("+")) {
                h = -1 * h;
                m = -1 * m;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(thedate);
            cal.add(Calendar.HOUR_OF_DAY, h);
            cal.add(Calendar.MINUTE, m);
            // calculate according the used timezone
            cal.add(Calendar.MILLISECOND, localTimeDiff(cal.getTimeZone(),
                    thedate));
            thedate = cal.getTime();
        }
        return thedate;
    }

    private static int localTimeDiff(TimeZone tz, Date date) {
        if (tz.inDaylightTime(date)) {
            int dstSavings = 0;
            if (tz.useDaylightTime()) {
                dstSavings = 3600000; // shortcut, JDK 1.4 allows cleaner impl
            }
            return tz.getRawOffset() + dstSavings;
        }
        return tz.getRawOffset();
    }
}