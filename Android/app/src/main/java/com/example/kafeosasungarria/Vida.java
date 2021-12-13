package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Vida {

    Context context;
    Bitmap vidaIrudia;
    int vidaX, vidaY;
    Random random;


    public Vida(Context context) {
        this.context = context;
        vidaIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.vida);
        random = new Random();
        vidaX = random.nextInt(JolasaView.pantallaAncho);
        vidaY =0;
    }

    public Bitmap getVidaIrudia(){
        return vidaIrudia;
    }

    int getVidaIrudiaAncho(){
        return vidaIrudia.getWidth();
    }

    int getVidaIrudiaAlto(){
        return vidaIrudia.getHeight();
    }

}