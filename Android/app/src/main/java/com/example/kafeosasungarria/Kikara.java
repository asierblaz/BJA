package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Kikara {

    Context context;
    Bitmap kikaraIrudia;
    int kikaraX, kikaraY;


    public Kikara(Context context) {
        this.context = context;
        kikaraIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.kikara3);
        kikaraX = (JolasaView.pantallaAncho-kikaraIrudia.getWidth())/2;
        kikaraY = JolasaView.pantallaAlto - kikaraIrudia.getHeight()-55;
    }

    public Bitmap getKikaraIrudia(){
        return kikaraIrudia;
    }

    int getKikaraIrudiaAncho(){
        return kikaraIrudia.getWidth();
    }

    int getKikaraIrudiaAlto(){
        return kikaraIrudia.getHeight();
    }

}
