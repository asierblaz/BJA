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
    Handler handler;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public JolasaView(Context context) {
        super(context);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);//obtener el tamaño de la pantalla
        pantallaAlto= size.y;
        pantallaAncho= size.x;
        handler = new Handler();


        kikara = new Kikara(context);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }

    protected void onDraw(Canvas canvas){
        //añadir el fondo al canvas
        canvas.drawBitmap(fondo,0,0,null);

       //para que siempre este en la pantalla
        if(kikara.kikaraX > pantallaAncho - kikara.getKikaraIrudiaAncho()){
            kikara.kikaraX = pantallaAncho - kikara.getKikaraIrudiaAncho();
        }else if(kikara.kikaraX < 0){
            kikara.kikaraX = 0;
        }

        //añadir la taza
        canvas.drawBitmap(kikara.getKikaraIrudia(), kikara.kikaraX, kikara.kikaraY, null);

        //se ejecuta el juego con el hilo
        handler.postDelayed(runnable, 10);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            kikara.kikaraX = touchX;
        }

        return true;
    }

}


