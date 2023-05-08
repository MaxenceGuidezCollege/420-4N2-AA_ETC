package com.pam.ex5_connexion;

import androidx.room.TypeConverter;

import java.sql.Date;

public class ConnexionConverter {

    @TypeConverter
    public static Date convert(long date){
        return new Date(date);
    }

    @TypeConverter
    public static long convert(Date date){
        return date.getTime();
    }
}
