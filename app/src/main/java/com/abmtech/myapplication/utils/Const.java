package com.abmtech.myapplication.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Const {
    public static String getCurrentTime() {
        Calendar c = Calendar.getInstance();
        String month = (c.get(Calendar.MONTH) + 1) + "";
        String day = c.get(Calendar.DAY_OF_MONTH) + "";
        String hour = c.get(Calendar.HOUR_OF_DAY) + "";
        String minute = c.get(Calendar.MINUTE) + "";

        if (month.length() == 1) month = "0" + month;
        if (day.length() == 1) day = "0" + day;
        if (hour.length() == 1) hour = "0" + hour;
        if (minute.length() == 1) minute = "0" + minute;

        return c.get(Calendar.YEAR) + "-" + month + "-" + day + "_" + hour + ":" + minute;
    }

    public static String getTimeAgo(String timeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm", Locale.getDefault());
        try {
            Date date = sdf.parse(timeString);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            Calendar now = Calendar.getInstance();

            if (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                long diffInMillis = now.getTimeInMillis() - calendar.getTimeInMillis();
                long hours = diffInMillis / (60 * 60 * 1000);
                long minutes = diffInMillis / (60 * 1000);
                if (hours > 0) {
                    return hours + " hours ago";
                } else if (minutes < 20) {
                    if (minutes < 2)
                        return "just now";
                    return minutes + " minutes ago";
                } else {
                    return "less than an hour ago";
                }
            }

            now.add(Calendar.DAY_OF_YEAR, -1);

            if (now.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
                    && now.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)) {
                return "Yesterday";
            }

            long timeInMillis = calendar.getTimeInMillis();
            long nowInMillis = now.getTimeInMillis();
            long diffInMillis = nowInMillis - timeInMillis;

            long days = diffInMillis / (24 * 60 * 60 * 1000);
            if (days > 0) {
                return days + " days ago";
            } else {
                long hours = diffInMillis / (60 * 60 * 1000);
                if (hours > 0) {
                    return hours + " hours ago";
                } else {
                    long minutes = diffInMillis / (60 * 1000);
                    return minutes + " minutes ago";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}
