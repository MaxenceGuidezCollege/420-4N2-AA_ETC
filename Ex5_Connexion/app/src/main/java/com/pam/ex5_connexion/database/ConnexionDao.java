package com.pam.ex5_connexion.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.pam.ex5_connexion.Connexion;

import java.util.Date;
import java.util.List;

@Dao
public abstract class ConnexionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract long insert(Connexion co);

    @Query(value = "SELECT * FROM connexions")
    public abstract List<Connexion> queryForAll();

    @Query(value = "SELECT * FROM connexions WHERE date = :date")
    public abstract Connexion queryByDate(int date);

//    @Query(value = "SELECT * FROM connexions WHERE MAX(date)")
    @Query(value = "SELECT * FROM connexions ORDER BY date DESC LIMIT 1")
    public abstract Connexion queryLastConnexion();

    @Query(value = "SELECT COUNT(date) FROM connexions")
    public abstract int countConnexion();
}
