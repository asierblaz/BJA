package com.example.kafeosasungarria;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    Button play;
    Partida p;
    TextView cutreCoin;
    TextView cutrecoinImg;
    TextView nombreJugador;
    TextView puntuacion;
    TextView hora;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        play= this.findViewById(R.id.play_btn);
        play.setOnClickListener(this::cambiar);
        p = (Partida) getIntent().getSerializableExtra("partida");
        guardarPartida(p);

        cutrecoinImg=findViewById(R.id.cutrecoin2);
        cutreCoin=findViewById(R.id.coinCantidad2);
        nombreJugador=findViewById(R.id.nombreJugadorTextview);
        puntuacion=findViewById(R.id.puntuazioaTextView);

        hora=findViewById(R.id.horaTextView);

        cutreCoin.setText(p.getJokalaria().getSaldo()+"");
        nombreJugador.setText(p.getJokalaria().getName());
        puntuacion.setText("Puntuazioa:\t "+p.getPuntuacion());
        hora.setText(p.printFecha());


    }
    public void cambiar(View v){
        Intent cambiar =  new Intent(this, Jolastu.class);
        cambiar.putExtra("jokalaria",p.getJokalaria());
        startActivity(cambiar);
    }


    public void guardarPartida(Partida p){
        SQLiteDatabase db;
        db = openOrCreateDatabase("BJA", Context.MODE_PRIVATE, null);
        db.execSQL("INSERT INTO Partida (idjugador,puntuacion,fecha) VALUES ('"+ p.getJokalaria().getDni()+"', '"+p.getPuntuacion()+"','"+p.printFecha()+"')");

        int saldoNew = p.getJokalaria().getSaldo()+ p.getPuntuacion();
        p.getJokalaria().setSaldo(saldoNew);
        db.execSQL("UPDATE Jugador SET saldo="+p.getJokalaria().getSaldo()+" WHERE dni='"+p.getJokalaria().getDni()+"'");

        DataConnect dt = new DataConnect(this);
        dt.connect2();
        if(dt.isStatus()) {
            dt.partidakToPostgre();
        }

   /*     UPDATE table_name
        SET column1 = value1, column2 = value2, ...
        WHERE condition;*/

    };


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        startActivity( new Intent(this,MainActivity.class));
        return true;
    }
}