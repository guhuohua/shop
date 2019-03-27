package com.wxapp.shopapp.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-01");



    public static long getTodayTimestamp() {
        LocalDate localDate = LocalDate.now();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant()).getTime();
    }

    public static long getMonthTimestamp() {
        Date d = new Date(System.currentTimeMillis());
        String s = format.format(d);
        try {
            return format.parse(s).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 2);
        return cal.getTime();
    }

    public static Date minusDay(Date date, int day) {
        return addDay(date, -day);
    }


}
