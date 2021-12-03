package com.example.kafeosasungarria;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Kikara {

    Context context;
    Bitmap kikaraIrudia;
    int kikaraX, kikaraY;
    Random random;


    public Kikara(Context context) {
        this.context = context;
        kikaraIrudia = BitmapFactory.decodeResource(context.getResources(), R.drawable.kikara);
        random = new Random();
        kikaraX = random.nextInt(JolasaView.pantallaAncho);
        kikaraY = JolasaView.pantallaAlto - kikaraIrudia.getHeight();
    }

    public Bitmap getKikaraIrudia(){
        return kikaraIrudia;
    }

    int getKikaraIrudiaAncho(){
        return kikaraIrudia.getWidth();
    }
}
