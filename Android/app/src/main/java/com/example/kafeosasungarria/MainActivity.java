package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView jolastuButton;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SQLite datu basea sortu edo atzitu
        db = openOrCreateDatabase("BJA", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS jugador(dni VARCHAR(9) PRIMARY KEY, name VARCHAR, surname VARCHAR, saldo int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS taza(id int PRIMARY KEY, foto VARCHAR, precio int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS partida(id int PRIMARY KEY, idjugador VARCHAR(9), puntuacion int, FOREIGN KEY (idjugador) REFERENCES jugador(dni));");
        db.execSQL("CREATE TABLE IF NOT EXISTS cosmetico(idjugador int NOT NULL, idtaza int NOT NULL, actual boolean, FOREIGN KEY (idjugador) REFERENCES jugador(dni), FOREIGN KEY (idtaza) REFERENCES taza(id), primary key (idjugador, idtaza));");
        db.execSQL("CREATE TABLE IF NOT EXISTS login(dni VARCHAR(9));");

        jolastuButton=this.findViewById(R.id.jolastuText);
        jolastuButton.setOnClickListener(this::jolastu);
    }


    public  void jolastu (View v){
        startActivity(new Intent(this, Jolastu.class));
     //   finish();

    }

}