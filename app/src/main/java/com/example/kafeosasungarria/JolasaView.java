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
   static Context c;
    Bitmap fondo;
    static int pantallaAncho, pantallaAlto;
    Kikara kikara;
    Azucar azucar;
    Esnea esnea;
    Handler handler;
    int velocidad=10;
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

        esnea= new Esnea(context);
        azucar = new Azucar(context);
        //c=context;
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }

    protected void onDraw(Canvas canvas){
        //añadir el fondo al canvas
        Log.d("a","1");
        canvas.drawBitmap(fondo,0,0,null);
        crearTaza(canvas);

        crearAzucar(canvas);

        crearEsnea(canvas);

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
            esnea.esneaY +=velocidad;
   }

   public void crearTaza(Canvas canvas){
       if(kikara.kikaraX > pantallaAncho - kikara.getKikaraIrudiaAncho()){
           kikara.kikaraX = pantallaAncho - kikara.getKikaraIrudiaAncho();
       }else if(kikara.kikaraX < 0){
           kikara.kikaraX = 0;
       }

       //añadir la taza
       canvas.drawBitmap(kikara.getKikaraIrudia(), kikara.kikaraX, kikara.kikaraY, null);
   }

   public void crearAzucar(Canvas canvas){
        //azucar= new Azucar(c);

       if(azucar.azucarX > pantallaAncho - azucar.getAzucarIrudiaAncho()){
           azucar.azucarX = pantallaAncho - azucar.getAzucarIrudiaAncho();
       }else if(azucar.azucarX < 0){
           azucar.azucarX = 0;
       }

       canvas.drawBitmap(azucar.getAzucarIrudia(), azucar.azucarX, azucar.azucarY, null);
   }

   public  void crearEsnea(Canvas canvas){
        //esnea = new Esnea(c);
       if(esnea.esneaX > pantallaAncho - esnea.getEsneaIrudiaAncho()){
           esnea.esneaX = pantallaAncho - esnea.getEsneaIrudiaAncho();
       }else if(azucar.azucarX < 0){
           azucar.azucarX = 0;
       }
       canvas.drawBitmap(esnea.getEsneaIrudia(), esnea.esneaX, esnea.esneaY, null);
   }

    public void froga(Canvas canvas) {
        Thread hilo = new Thread(() ->
        {


        });
    }
}


