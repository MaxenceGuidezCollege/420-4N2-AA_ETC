package com.pam.ex5_connexion;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.sql.Date;

@Entity(tableName = "connexions")
public class Connexion {
    @PrimaryKey(autoGenerate = true)
    @TypeConverters(value = {ConnexionConverter.class})
    private Date date;

    public Connexion(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Connexion{" +
                "date=" + date +
                '}';
    }
}
