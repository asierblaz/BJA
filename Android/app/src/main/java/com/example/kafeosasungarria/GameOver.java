package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.os.Bundle;

public class GameOver extends AppCompatActivity {

=======
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class GameOver extends AppCompatActivity {

    Button play;
    Partida p;
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
<<<<<<< Updated upstream
=======
        play= this.findViewById(R.id.play_btn);
        play.setOnClickListener(this::cambiar);
        p = (Partida) getIntent().getSerializableExtra("partida");
        guardarPartida(p);
    }
    public void cambiar(View v){
        Intent cambiar =  new Intent(this, Jolastu.class);
        cambiar.putExtra("jokalaria",p.getJokalaria());
        startActivity(cambiar);
>>>>>>> Stashed changes
    }


    public void guardarPartida(Partida p){
        SQLiteDatabase db;
        db = openOrCreateDatabase("BJA", Context.MODE_PRIVATE, null);
        db.execSQL("INSERT INTO Partida (idjugador,puntuacion) VALUES ('"+ p.getJokalaria().getDni()+"', '"+p.getPuntuacion()+"')");




    };
}