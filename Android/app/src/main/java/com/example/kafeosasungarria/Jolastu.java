package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Jolastu extends AppCompatActivity {

    TextView play;
    Jokalaria j;
    static  Partida p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jolastu);
        play= this.findViewById(R.id.textViewEmpezar);
        play.setOnClickListener(this::cambiar);
        j = (Jokalaria) getIntent().getSerializableExtra("jokalaria");

    }


    public void cambiar(View v){
        p = new Partida(j);
        Date fecha = new Date(System.currentTimeMillis());
        p.setFecha(fecha);
        Log.d("",p.printFecha());
        setContentView(new JolasaView(this));
    }

    public static Partida getPartida(){
        return p;
    }




}