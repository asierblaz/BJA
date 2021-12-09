package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Esnea {

    Context context;
    Bitmap EsneaIrudia;
    int esneaX, esneaY;
    Random random;


    public Esnea(Context context) {
        this.context = context;
        EsneaIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.leche);
        random = new Random();
        esneaX = random.nextInt(JolasaView.pantallaAncho);
        esneaY =0;
    }

    public Bitmap getEsneaIrudia(){
        return EsneaIrudia;
    }

    int getEsneaIrudiaAncho(){
        return EsneaIrudia.getWidth();
    }

    int getEsneaIrudiaAlto(){
        return EsneaIrudia.getHeight();
    }

}