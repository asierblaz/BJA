package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOver extends AppCompatActivity {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        play= this.findViewById(R.id.play_btn);
        play.setOnClickListener(this::cambiar);
    }
    public void cambiar(View v){
        startActivity(new Intent(this, Jolastu.class));
    }
}