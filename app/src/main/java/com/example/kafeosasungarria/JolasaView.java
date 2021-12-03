package com.example.kafeosasungarria;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


public class JolasaView extends View {

    Context context;
    Bitmap fondo;
    static int pantallaAncho, pantallaAlto;
    Kikara kikara;
    Azucar azucar;
    Handler handler;
    int velocidad=50;
    int vida=50;
    final Runnable haria = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
    final Runnable caida = new Runnable() {
        @Override
        public void run() {

            lanzar();

        }
    };

    public JolasaView(Context context) {
        super(context);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay(); //poner el lienzo en la pantalla
        Point size = new Point();
        display.getSize(size);//obtener el tamaño de la pantalla
        pantallaAlto= size.y;
        pantallaAncho= size.x;
        handler = new Handler();//evento para movimiento con el dedo
        kikara = new Kikara(context);
        azucar= new Azucar(context);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }

    protected void onDraw(Canvas canvas){
        //añadir el fondo al canvas
        Log.d("a","1");
        canvas.drawBitmap(fondo,0,0,null);

        //------------- TAZA -----------------------

       //para que siempre este en la pantalla
        if(kikara.kikaraX > pantallaAncho - kikara.getKikaraIrudiaAncho()){
            kikara.kikaraX = pantallaAncho - kikara.getKikaraIrudiaAncho();
        }else if(kikara.kikaraX < 0){
            kikara.kikaraX = 0;
        }

        //añadir la taza
        canvas.drawBitmap(kikara.getKikaraIrudia(), kikara.kikaraX, kikara.kikaraY, null);

        //------------------- AZUCAR --------------------------------------
        //para que siempre este en la pantalla
        if(azucar.azucarX > pantallaAncho - azucar.getAzucarIrudiaAncho()){
            azucar.azucarX = pantallaAncho - azucar.getAzucarIrudiaAncho();
        }else if(azucar.azucarX < 0){
            azucar.azucarX = 0;
        }

        /*for(int i=0; i < azucar.size(); i++){
            ourShots.get(i).shy -= 15;
            canvas.drawBitmap(ourShots.get(i).getShot(), ourShots.get(i).shx, ourShots.get(i).shy, null);
            if((ourShots.get(i).shx >= enemySpaceship.ex)
                    && ourShots.get(i).shx <= enemySpaceship.ex + enemySpaceship.getEnemySpaceshipWidth()
                    && ourShots.get(i).shy <= enemySpaceship.getEnemySpaceshipWidth()
                    && ourShots.get(i).shy >= enemySpaceship.ey){
                points++;
                ourShots.remove(i);
                explosion = new Explosion(context, enemySpaceship.ex, enemySpaceship.ey);
                explosions.add(explosion);
            }else if(ourShots.get(i).shy <=0){
                ourShots.remove(i);
            }
        }*/


        //añadir azucar
        canvas.drawBitmap(azucar.getAzucarIrudia(), azucar.azucarX, azucar.azucarY, null);


        // se ejecuta el juego con el hilo
        caida.run();
        handler.postDelayed(haria, 10);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            kikara.kikaraX = touchX;
        }

        return true;
    }

   public void lanzar(){
      /*  Log.d("op",o.getClass().getName()+"");
        if(o.getClass().getName()+""=="com.example.kafeosasungarria.Azucar"){


        }*/ azucar.azucarY +=velocidad;
   }

}


