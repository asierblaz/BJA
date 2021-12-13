package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Azucar {

    Context context;
    Bitmap AzucarIrudia;
    int azucarX, azucarY;
    Random random;


    public Azucar(Context context) {
        this.context = context;
        AzucarIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.azucar);
        random = new Random();
        azucarX = random.nextInt(JolasaView.pantallaAncho);
        azucarY =0;
    }

    public Bitmap getAzucarIrudia(){
        return AzucarIrudia;
    }

    int getAzucarIrudiaAncho(){
        return AzucarIrudia.getWidth();
    }
}