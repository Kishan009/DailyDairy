package com.dailymood.tracker.daily.diary.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public static Date getDate(int min, int hour, int day, int month, int year)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strdate = year + "-" + month + "-" + day + " " + hour + ":" + min;
        Date date = null;
        try {
            date = simpleDateFormat.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String getBriefDate(long date) {
        Date date1 = new Date(date);
        String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", date1);
        String monthString = (String) android.text.format.DateFormat.format("MMMM", date1);
        String year = (String) android.text.format.DateFormat.format("yyyy", date1);
        String day = (String) android.text.format.DateFormat.format("dd", date1);
        return dayOfTheWeek + ", " + day + " " + monthString + " " + year;

    }

    public static String getTime(long date) {
        Date date1 = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(date1);
    }

    public static int getYear(long date) {
        Date date1 = new Date(date);
        return Integer.parseInt(android.text.format.DateFormat.format("yyyy", date1).toString());
    }

    public static int getMonth(long date) {
        Date date1 = new Date(date);
        return Integer.parseInt(android.text.format.DateFormat.format("MM", date1).toString());

    }

    public static int getday(long date) {
        Date date1 = new Date(date);
        return Integer.parseInt(android.text.format.DateFormat.format("dd", date1).toString());

    }


    public static int getHour(long date) {
        Date date1 = new Date(date);
        return Integer.parseInt(android.text.format.DateFormat.format("HH", date1).toString());

    }

    public static int getMinute(long date) {
        Date date1 = new Date(date);
        return Integer.parseInt(android.text.format.DateFormat.format("mm", date1).toString());

    }

}
