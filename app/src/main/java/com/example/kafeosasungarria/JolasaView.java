package com.example.kafeosasungarria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;


public class JolasaView extends View {

    Bitmap fondo, lifeImg;
    int cont=0;
    static int pantallaAncho, pantallaAlto;
    ArrayList<Azucar> azucares;
    ArrayList<Esnea> leches;
    ArrayList<Cafe> cafes;
    ArrayList<Vida> vidas;//las vidas que caen
    Kikara kikara;
    Azucar azucar;
    Vida vida;
    Esnea esnea;
    Cafe cafe;
    Handler handler;
    int velocidad=10;
    int life =5;
    int puntuacion=0;
    Paint paintPuntuacion;
    boolean amaitu=false;


    final Runnable haria = new Runnable() {
        @Override
        public void run() {
            invalidate();
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
        cafe = new Cafe(context);
        azucares= new ArrayList<Azucar>();
        leches= new ArrayList<Esnea>();
        vidas= new ArrayList<Vida>();//son las vidas que caen
        cafes= new ArrayList<Cafe>();
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
        lifeImg = BitmapFactory.decodeResource(context.getResources(), R.drawable.vida);
        paintPuntuacion = new Paint();
        paintPuntuacion.setColor(Color.GREEN);
        paintPuntuacion.setTextSize(80);
        paintPuntuacion.setTextAlign(Paint.Align.LEFT);
    }


    protected void onDraw(Canvas canvas){
        Random random= new Random();

        //añadir el fondo al canvas
        canvas.drawBitmap(fondo,0,0,null);
        canvas.drawText(puntuacion+"", pantallaAncho-100, 80, paintPuntuacion);
        for(int i = life; i>=1; i--){
            canvas.drawBitmap(lifeImg, (lifeImg.getWidth() * i)- lifeImg.getWidth()+30, 20, null);
        }
        crearTaza(canvas);

//        if(random.nextInt(3)==)

        /* switch (random.nextInt(10)){
            case 0:addCafe();
            break;
            case 1:addEsnea();
            break;
            case 2:addAzucar();
            break;
            default:break;
        }
*/
        if(cont % 26 ==0){
            addEsnea();

        }
       if(cont % 27 ==0){
            addCafe();
        }
        if(cont % 33 ==0){
            addAzucar();
        }
        if(cont % 597==0){
            addVida();;
        }
      if(cont % 50==0){
            velocidad++;
        }
//---------------ESNEA----------------------------------

        for(int i =0; i<leches.size();i++){

            if(leches.get(i).esneaX > pantallaAncho - leches.get(i).getEsneaIrudiaAncho()){
                leches.get(i).esneaX = pantallaAncho - leches.get(i).getEsneaIrudiaAncho();
            }else if(leches.get(i).esneaX < 0){
                leches.get(i).esneaX = 0;
            }

            canvas.drawBitmap(leches.get(i).getEsneaIrudia(), leches.get(i).esneaX, leches.get(i).esneaY, null);
            leches.get(i).esneaY +=velocidad;



            if((leches.get(i).esneaY >= kikara.kikaraY)
                    && leches.get(i).esneaY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                    && leches.get(i).esneaX <= kikara.kikaraX + kikara.getKikaraIrudiaAncho()
                    && leches.get(i).esneaX >= kikara.kikaraX
                    && leches.get(i).esneaX <= pantallaAncho){
                puntuacion++;
                leches.remove(i);
            }else if(leches.get(i).esneaY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                leches.remove(i);
            }

        }

//----------------------Caida de vidas------------------------------

        for(int i =0; i<vidas.size();i++){

            if(vidas.get(i).vidaX > pantallaAncho - leches.get(i).getEsneaIrudiaAncho()){
                vidas.get(i).vidaX = pantallaAncho - leches.get(i).getEsneaIrudiaAncho();
            }else if(vidas.get(i).vidaX < 0){
                vidas.get(i).vidaX = 0;
            }

            canvas.drawBitmap(vidas.get(i).getVidaIrudia(), vidas.get(i).vidaX, vidas.get(i).vidaY, null);
            vidas.get(i).vidaY +=velocidad;



            if((vidas.get(i).vidaY >= kikara.kikaraY)
                    && vidas.get(i).vidaY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                    && vidas.get(i).vidaX <= kikara.kikaraX + kikara.getKikaraIrudiaAncho()
                    && vidas.get(i).vidaX >= kikara.kikaraX
                    && vidas.get(i).vidaX <= pantallaAncho){
                life++;
                vidas.remove(i);
            }else if(vidas.get(i).vidaY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                vidas.remove(i);
            }

        }

//---------------CAFE----------------------------------

        for(int i =0; i<cafes.size();i++){
            if(cafes.get(i).cafeX > pantallaAncho - cafes.get(i).getCafeIrudiaAncho()){
                cafes.get(i).cafeX = pantallaAncho - cafes.get(i).getCafeIrudiaAncho();
            }else if(cafes.get(i).cafeX < 0){
                cafes.get(i).cafeX = 0;
            }

            canvas.drawBitmap(cafes.get(i).getCafeIrudia(), cafes.get(i).cafeX, cafes.get(i).cafeY, null);
            cafes.get(i).cafeY +=velocidad;



            if((cafes.get(i).cafeY >= kikara.kikaraY)
                    && cafes.get(i).cafeY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                    && cafes.get(i).cafeX <= kikara.kikaraX + kikara.getKikaraIrudiaAncho()
                    && cafes.get(i).cafeX >= kikara.kikaraX
                    && cafes.get(i).cafeX <= pantallaAncho){
                puntuacion++;
                cafes.remove(i);
            }else if(cafes.get(i).cafeY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                cafes.remove(i);
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


          if((azucares.get(i).azucarY >= kikara.kikaraY)
                  && azucares.get(i).azucarY <= kikara.kikaraY + kikara.getKikaraIrudiaAlto()
                  && azucares.get(i).azucarX <= kikara.kikaraX + kikara.getKikaraIrudiaAncho()
                  && azucares.get(i).azucarX >= kikara.kikaraX
                  && azucares.get(i).azucarX <= pantallaAncho){
                life--;
                azucares.remove(i);
            }else if(azucares.get(i).azucarY >= (pantallaAlto)) { //para eliminar los que se salen de la pantalla
                   azucares.remove(i);
               }

        }

        if(life <=0){
            amaitu= true;
            handler= null;
            Intent gameOver = new Intent(getContext(),GameOver.class);
           getContext().startActivity(gameOver);
            ((Activity) getContext()).finish();

        }

        if(!amaitu) {
            cont++;
            handler.postDelayed(haria, 10);
        }


    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();

        if(event.getAction() == MotionEvent.ACTION_MOVE){
            kikara.kikaraX = touchX;
        }
        return true;
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
        Azucar a= new Azucar(getContext());
        azucares.add(a);
    }
    public void addVida(){
        Vida v= new Vida(getContext());
        vidas.add(v);
    }
    public void addEsnea(){
        Esnea e= new Esnea(getContext());
        leches.add(e);
    }
    public void addCafe(){
        Cafe cafe= new Cafe(getContext());
        cafes.add(cafe);
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


