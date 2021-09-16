package com.ifi.javacore.management.covid.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {
    private static String formatter = "dd/MM/yyyy";
    private static String formatter2 = "dd/MM/yyyy hh:mm:ss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    public static String dateToString(Date date){
        return simpleDateFormat.format(date);
    }
    public static Date StringToDate(String strDate) throws ParseException {
        Date date1=simpleDateFormat.parse(strDate);
        return date1;
    }

    public static Date StringToDate2(String strDate) {
        Date date1= null;
        try {
            date1 = new SimpleDateFormat(formatter2).parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;
    }
    public static String DateToString2(Date date){
        return new SimpleDateFormat(formatter2).format(date);
    }


    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println(DateToString2(date));

    }
}
