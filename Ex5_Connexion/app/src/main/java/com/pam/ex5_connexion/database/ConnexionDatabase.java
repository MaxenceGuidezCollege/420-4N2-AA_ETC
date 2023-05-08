package com.pam.ex5_connexion.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.pam.ex5_connexion.Connexion;

@Database(entities = {Connexion.class}, version = 2)
public abstract class ConnexionDatabase extends RoomDatabase {

    public abstract ConnexionDao getConnexionDao();
}
