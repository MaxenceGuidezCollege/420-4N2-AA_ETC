package com.pam.ex5_connexion;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.pam.ex5_connexion.database.ConnexionDatabase;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "connexion.db";
    private ConnexionDatabase db;
    private TextView tv;
    private Connexion actualCo;
    private Connexion lastCo;
    private int nbrCo;

    /**
     * Au démarrage de l'application, on initialise l'accès à la base de données.
     * Cette dernière est fermée dans la méthode onStop().
     */
    @Override
    protected void onStart() {
        super.onStart();

        initDatabase();
    }
    /**
     * À la fermeture de l'application, il faut fermer la base de donnée sinon il y aura une
     * surcharge de l'utilisation des ressources.
     */
    @Override
    protected void onStop() {
        super.onStop();

        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();

        tv = findViewById(R.id.tv);
        nbrCo = db.getConnexionDao().countConnexion();

        if(nbrCo == 0){
            tv.setText("Vous vous êtes connecté 1 fois\nCeci est votre première connexion.");
        }
        else{
            lastCo = db.getConnexionDao().queryLastConnexion();

//            String date = lastCo.getDate().toString();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
//            Date formate = null;
//            try {
//                 formate = sdf.parse(date);
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }

            tv.setText("Vous vous êtes connecté " + (nbrCo + 1) + " fois\n" +
                    "La dernière connexion date du " + lastCo.getDate());
        }

        java.util.Date dateU = java.util.Date.from(Instant.now());
        actualCo = new Connexion(new Date(dateU.getTime()));
        db.getConnexionDao().insert(actualCo);
    }

    /**
     * Créer la base de donnée.
     */
    private void initDatabase() {
        db = Room
                .databaseBuilder(this, ConnexionDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
}