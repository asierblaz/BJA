package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Jolastu extends AppCompatActivity {

    TextView play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jolastu);
        play= this.findViewById(R.id.textViewEmpezar);

        play.setOnClickListener(this::cambiar);

    }


    public void cambiar(View v){

        setContentView(new JolasaView(this));
    }



}