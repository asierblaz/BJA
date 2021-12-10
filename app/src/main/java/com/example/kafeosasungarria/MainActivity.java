package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView jolastuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jolastuButton=this.findViewById(R.id.jolastuText);
        jolastuButton.setOnClickListener(this::jolastu);
    }


    public  void jolastu (View v){
        startActivity(new Intent(this, Jolastu.class));
        finish();

    }

}