package com.example.kafeosasungarria;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class JolasaView extends View {

    Canvas canv;
   static Context c;
    Bitmap fondo;
    int cont=0;
    static int pantallaAncho, pantallaAlto;
    ArrayList<Azucar> azucares;
    ArrayList<Esnea> leches;
    Kikara kikara;
    Azucar azucar;
    Esnea esnea;
    Handler handler;
    int velocidad=10;
    int vida=50;
    int puntuacion=0;
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
        azucares= new ArrayList<Azucar>();
        leches= new ArrayList<Esnea>();
        c=context;
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
    }





    protected void onDraw(Canvas canvas){
        //añadir el fondo al canvas
        canvas.drawBitmap(fondo,0,0,null);
        canv= canvas;
        crearTaza(canvas);
      //  crearAzucar(canvas);

       // crearEsnea(canvas);

        // se ejecuta el juego con el hilo
        caida.run();
        //Log.d("",cont+"");

        if(cont % 27 ==0){
            //addAzucar();
            addEsnea();
        }
        if(cont % 25 ==0){
            addAzucar();
            //addEsnea();
        }
      /*  if(cont>200){
            velocidad= 30;
        }*/
//---------------ESNEA----------------------------------

        for(int i =0; i<leches.size();i++){

            if(leches.get(i).esneaX > pantallaAncho - leches.get(i).getEsneaIrudiaAncho()){
                leches.get(i).esneaX = pantallaAncho - leches.get(i).getEsneaIrudiaAncho();
            }else if(leches.get(i).esneaX < 0){
                leches.get(i).esneaX = 0;
            }

            canvas.drawBitmap(leches.get(i).getEsneaIrudia(), leches.get(i).esneaX, leches.get(i).esneaY, null);
            leches.get(i).esneaY +=velocidad;



            if((leches.get(i).esneaY >= kikara.kikaraY) && leches.get(i).esneaY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                    && leches.get(i).esneaX >= kikara.kikaraX
                    && leches.get(i).esneaY <= pantallaAlto){
                puntuacion++;
                Log.d("vida",puntuacion+"");
                leches.remove(i);
            }else if(leches.get(i).esneaY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                leches.remove(i);
            }

        }


     //-------------AZUCAR--------------------------------

      for(int i =0; i<azucares.size();i++){

           if(azucares.get(i).azucarX > pantallaAncho - azucares.get(i).getAzucarIrudiaAncho()){
                azucares.get(i).azucarX = pantallaAncho - azucares.get(i).getAzucarIrudiaAncho();
            }else if(azucares.get(i).azucarX < 0){
                azucares.get(i).azucarX = 0;
            }

            canvas.drawBitmap(azucares.get(i).getAzucarIrudia(), azucares.get(i).azucarX, azucares.get(i).azucarY, null);
            azucares.get(i).azucarY +=velocidad;



          if((azucares.get(i).azucarY >= kikara.kikaraY) && azucares.get(i).azucarY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                && azucares.get(i).azucarX >= kikara.kikaraX
                && azucares.get(i).azucarY <= pantallaAlto){
                vida--;
                Log.d("vida",vida+"");
                azucares.remove(i);
            }else if(azucares.get(i).azucarY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                   azucares.remove(i);
               }

        }


        cont++;
        handler.postDelayed(haria, 10);



    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            kikara.kikaraX = touchX;
            //crearAzucar2(canv);
        }
        return true;
    }

   public void lanzar(){
      /*  Log.d("op",o.getClass().getName()+"");
        if(o.getClass().getName()+""=="com.example.kafeosasungarria.Azucar"){
        }*/
       azucar.azucarY +=velocidad;
    //   esnea.esneaY +=velocidad;
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
       if(azucar.azucarX > pantallaAncho - azucar.getAzucarIrudiaAncho()){
           azucar.azucarX = pantallaAncho - azucar.getAzucarIrudiaAncho();
       }else if(azucar.azucarX < 0){
           azucar.azucarX = 0;
       }

       canvas.drawBitmap(azucar.getAzucarIrudia(), azucar.azucarX, azucar.azucarY, null);
   }

    public void addAzucar(){
        Azucar a= new Azucar(c);
        azucares.add(a);
    }
    public void addEsnea(){
        Esnea e= new Esnea(c);
        leches.add(e);
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


}


