package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Cafe {

    Context context;
    Bitmap CafeIrudia;
    int cafeX, cafeY;
    Random random;


    public Cafe(Context context) {
        this.context = context;
        CafeIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.kafea);
        random = new Random();
        cafeX = random.nextInt(JolasaView.pantallaAncho);
        cafeY =0;
    }

    public Bitmap getCafeIrudia(){
        return CafeIrudia;
    }

    int getCafeIrudiaAncho(){
        return CafeIrudia.getWidth();
    }

    int getCafeIrudiaAlto(){
        return CafeIrudia.getHeight();
    }

}