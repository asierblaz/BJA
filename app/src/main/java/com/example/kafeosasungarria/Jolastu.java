package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Jolastu extends AppCompatActivity {

    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jolastu);
        play= this.findViewById(R.id.buttonEmpezarJuego);

        play.setOnClickListener(this::cambiar);

    }


    public void cambiar(View v){

        setContentView(new JolasaView(this));
    }



}