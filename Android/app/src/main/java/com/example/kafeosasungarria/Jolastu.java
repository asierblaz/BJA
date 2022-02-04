package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Jolastu extends AppCompatActivity {

    TextView play;
    Jokalaria j;
    static  Partida p;
    public static int tactual = 0;
   static ArrayList<Bitmap> tazas = new ArrayList<android.graphics.Bitmap>();
    ImageView imageTaza;
    Button b1, b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jolastu);
        play= this.findViewById(R.id.textViewEmpezar);
        imageTaza = this.findViewById(R.id.imageTaza);
        play.setOnClickListener(this::cambiar);
        j = (Jokalaria) getIntent().getSerializableExtra("jokalaria");
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikarakiker));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaracolacao));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikara2));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikara3));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaraeibar));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaraestrella));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaragithub));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaraspring));
        tazas.add(BitmapFactory.decodeResource(this.getResources(), R.drawable.kikaravisual));
        imageTaza.setImageBitmap(tazas.get(tactual)) ;

        b1 = findViewById(R.id.adelanteBtn);
        b2 = findViewById(R.id.atrasBtn);

        b1.setOnClickListener(this::tazaAdelante);
        b2.setOnClickListener(this::tazaAtras);

    }


    public void tazaAdelante(View v) {
        tactual++;
        if(tactual == tazas.size()) {
            tactual= 0;
        }
        imageTaza.setImageBitmap(tazas.get(tactual)) ;


    }
    public void tazaAtras (View v) {
        tactual--;
        if(tactual < 0 ) {
            tactual= tazas.size()-1;
        }
        imageTaza.setImageBitmap(tazas.get(tactual)) ;

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