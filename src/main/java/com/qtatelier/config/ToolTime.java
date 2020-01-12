package com.qtatelier.config;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @description: 时间工具
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-13 15:25
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
public class ToolTime {
    public static final String ALL_TIME = "yyyyMMddHHmmss";
    public static final String DATE_TIME = "yyyyMMdd";
    public static final String YEAR_MONTH = "yyyyMM";
    public static final String DATE_TIME_SPACING = "yyyy-MM-dd";
    public static final String ALL_TIME_SPACING = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<SimpleDateFormat> dateFrt = new ThreadLocal();

    public ToolTime() {
    }

    public static String getNowStringByAllTime() {
        return getStringByAllTimeSpacing(new Date(), "yyyyMMddHHmmss");
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(getNowDate().getTime());
    }

    public static Date getNowDate() {
        return new Date();
    }

    public static String getStringByAllTime(Date date) {
        return getStringByAllTimeSpacing(date, "yyyyMMddHHmmss");
    }

    public static Date getDateByAllTime(String dateStr) throws ParseException {
        return getDateByAllTimeSpacing(dateStr, "yyyyMMddHHmmss");
    }

    public static String getYearAndMoush() throws ParseException {
        return getStringByAllTimeSpacing(getNowDate(), "yyyyMM");
    }

    public static String getNowStringByAllTimeSpacing() {
        return getStringByAllTimeSpacing(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getStringByAllTimeSpacing(Date date) {
        return getStringByAllTimeSpacing(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getDateByAllTimeSpacing(String dateStr) throws ParseException {
        return getDateByAllTimeSpacing(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getStringByAllTimeSpacing(Date date, String sdf) {
        return getSdf(sdf).format(date);
    }

    public static Date getDateByAllTimeSpacing(String dateStr, String sdf) throws ParseException {
        return getSdf(sdf).parse(dateStr);
    }

    private static SimpleDateFormat getSdf(String sdf) {
        if (null == dateFrt.get() || !((SimpleDateFormat)dateFrt.get()).toPattern().equals(sdf)) {
            dateFrt.set(new SimpleDateFormat(sdf));
        }

        return (SimpleDateFormat)dateFrt.get();
    }

    public static String format(String formatStr, int field, int offset) {
        return format(new Date(), formatStr, field, offset);
    }

    public static String format(Date dateTime, String formatStr, int field, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        if (field != -1) {
            cal.add(field, offset);
        }

        SimpleDateFormat dateFormat = getSdf(formatStr);
        return dateFormat.format(cal.getTime());
    }

    public static Date specifyTime(Date dateTime, int year, int month, int day, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        if (year != -1) {
            cal.set(1, year);
        }

        if (month != -1) {
            cal.set(2, month);
        }

        if (day != -1) {
            cal.set(5, day);
        }

        if (hour != -1) {
            cal.set(11, hour);
        }

        if (minute != -1) {
            cal.set(12, minute);
        }

        if (second != -1) {
            cal.set(13, second);
        }

        return cal.getTime();
    }

    public static long getDistDates(String startDate, String endDate) throws ParseException {
        long totalDate = 0L;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDateByAllTime(startDate));
        long timestart = calendar.getTimeInMillis();
        calendar.setTime(getDateByAllTime(endDate));
        long timeend = calendar.getTimeInMillis();
        totalDate = Math.abs(timeend - timestart) / 86400000L;
        return totalDate;
    }

    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(7) - 1;
        return dayOfWeek == 1 ? 0 : 1 - dayOfWeek;
    }

    public static String getMondayOFWeek() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(5, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public static String getMonthFirstDay() {
        Calendar cal_1 = Calendar.getInstance();
        cal_1.add(2, -1);
        cal_1.set(5, 1);
        return getSdf("yyyy-MM-dd HH:mm:ss").format(cal_1.getTime());
    }

    public static String getMonthLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.set(5, 0);
        return getSdf("yyyy-MM-dd HH:mm:ss").format(cale.getTime());
    }

    public static Date getDateByString_spacing(String data) throws ParseException {
        return getDateByAllTimeSpacing(data, "yyyy-MM-dd");
    }

    public static String addLastTime(String time, String sdf) {
        try {
            Date date = null;
            if (StringUtils.equals(sdf, "yyyy-MM-dd")) {
                date = getDateByString_spacing(time);
            }

            if (date != null) {
                date.setHours(23);
                date.setMinutes(59);
                date.setSeconds(59);
                return getStringByAllTime(date);
            }
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        return time;
    }

    public static boolean isLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5) == calendar.getActualMaximum(5);
    }
}

