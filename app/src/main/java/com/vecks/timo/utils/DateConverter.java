package com.vecks.timo.utils;

import android.widget.CalendarView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by carlos on 11/12/17.
 */

public class DateConverter {


    public static Date stringToDate(String dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String dateToString(Date date){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(date.getTime());

        return s;
    }

    public static Date calendarToDate(CalendarView view){
        return new Date(view.getDate());
    }
}
