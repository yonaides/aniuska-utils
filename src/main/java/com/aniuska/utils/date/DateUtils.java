/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hectorvent@gmail,com
 */
public class DateUtils {

    public static String date2String(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    public static DateRange string2DateRange(String dateRange) {
        try {
            DateRange dr = new DateRange();
            String[] ds = dateRange.split("-");

            dr.setFirstDate(new SimpleDateFormat("ddMMyyyy").parse(ds[0]));
            dr.setEndDate(new SimpleDateFormat("ddMMyyyy").parse(ds[1]));

            return dr;
        } catch (ParseException ex) {
//            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static DateRange string2DateRangeTime(String dateRange) {
        try {
            DateRange dr = new DateRange();
            String[] ds = dateRange.split("-");

            dr.setFirstDate(new SimpleDateFormat("ddMMyyyyhh:mma").parse(ds[0]));
            dr.setEndDate(new SimpleDateFormat("ddMMyyyyhh:mma").parse(ds[1]));

            return dr;
        } catch (ParseException ex) {
//            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Date string2Date(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException ex) {
//            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }

    public static String dateTime2String(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy hh:mm a").format(date);
    }

    public static Date string2DateTime(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(date);
        } catch (ParseException ex) {
        }
        return new Date();
    }

    public static void main(String[] args) throws ParseException {

        String d = "0512201502:25PM";

        System.out.println(new SimpleDateFormat("ddMMyyyyhh:mma").parse(d));

    }

}
