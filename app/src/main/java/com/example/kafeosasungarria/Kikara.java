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
        kikaraX = random.nextInt(Jolasa.pantallaAncho);
        kikaraY = Jolasa.pantallaAlto - kikaraIrudia.getHeight();
    }

    public Bitmap getKikaraIrudia(){
        return kikaraIrudia;
    }

    int getKikaraIrudiaAncho(){
        return kikaraIrudia.getWidth();
    }
}

    /*public class OurSpaceship {
    Context context;
    Bitmap ourSpaceship;
    int ox, oy;
    Random random;

    public OurSpaceship(Context context) {
        this.context = context;
        ourSpaceship = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket1);
        random = new Random();
        ox = random.nextInt(SpaceShooter.screenWidth);
        oy = SpaceShooter.screenHeight - ourSpaceship.getHeight();
    }

    public Bitmap getOurSpaceship(){
        return ourSpaceship;
    }

    int getOurSpaceshipWidth(){
        return ourSpaceship.getWidth();
    }
}
*/

