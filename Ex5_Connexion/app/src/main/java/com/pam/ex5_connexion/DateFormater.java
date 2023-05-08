package com.pam.ex5_connexion;

import java.util.Date;

public class DateFormater {

    public static String format(Date date){

        int day = date.getDay() + 7;
        int month = date.getMonth() + 1;
        int year = date.getYear() + 1900;

        String zeroH = "";
        String zeroM = "";
        if(date.getHours() < 10) zeroH = "0";
        if(date.getMinutes() < 10) zeroM = "0";

        String hour = zeroH + (date.getHours() - 4);
        String min = zeroM + date.getMinutes();

        return day + "/" + month + "/" + year + " " + hour + ":" + min;
    }
}
