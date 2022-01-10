package com.example.kafeosasungarria;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< Updated upstream
import android.content.Intent;
=======
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
>>>>>>> Stashed changes
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    TextView jolastuButton;
<<<<<<< Updated upstream
=======
    SQLiteDatabase db;
    TextView loginText;
    TextView saioaText;
    TextView editDni;
    TextView errorMensaje;
    TextView cerrarSesion;
    TextView userFoto;
    TextView cutreCoinCantidad;
    TextView cutreCoin;
    Jokalaria j;

>>>>>>> Stashed changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< Updated upstream
        jolastuButton=this.findViewById(R.id.jolastuText);
=======
        // SQLite datu basea sortu edo atzitu
        db = openOrCreateDatabase("BJA", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS jugador(dni VARCHAR(9) PRIMARY KEY, name VARCHAR, surname VARCHAR, saldo int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS taza(id int PRIMARY KEY, foto VARCHAR, precio int);");
        db.execSQL("CREATE TABLE IF NOT EXISTS partida(id Integer PRIMARY KEY AUTOINCREMENT, idjugador VARCHAR(9), puntuacion int, FOREIGN KEY (idjugador) REFERENCES jugador(dni));");
        db.execSQL("CREATE TABLE IF NOT EXISTS cosmetico(idjugador int NOT NULL, idtaza int NOT NULL, actual boolean, FOREIGN KEY (idjugador) REFERENCES jugador(dni), FOREIGN KEY (idtaza) REFERENCES taza(id), primary key (idjugador, idtaza));");
        db.execSQL("CREATE TABLE IF NOT EXISTS login(dni VARCHAR(9));");
        //db.execSQL("INSERT INTO jugador VALUES ('123','pablo','p',1)");


        jolastuButton=this.findViewById (R.id.jolastuText);
>>>>>>> Stashed changes
        jolastuButton.setOnClickListener(this::jolastu);

        saioaText=findViewById(R.id.textSaioaHasi);
        editDni=findViewById(R.id.editTextDNI);
        errorMensaje=findViewById(R.id.textError);
        loginText=findViewById(R.id.loginText);
        loginText.setOnClickListener(this::login);
        userFoto= findViewById(R.id.user);
        cutreCoin=findViewById(R.id.cutrecoin);
        cutreCoinCantidad=findViewById(R.id.coinCantidad);

        cerrarSesion= findViewById(R.id.cerrarSesionText);
        cerrarSesion.setOnClickListener(this::cerrarSesion);

    }


    public  void jolastu (View v){

        Intent intent = new Intent(MainActivity.this, Jolastu.class);
        intent.putExtra("jokalaria",j);
        startActivity(intent);
     //   finish();

    }


    public void login (View v){

        boolean error= true;

        Cursor c = db.rawQuery("SELECT * FROM jugador WHERE dni = '" + editDni.getText().toString()+ "'", null);
        if(c.getCount() > 0){
            error = false;
            while(c.moveToNext()) {
                j= new Jokalaria();

               j.setDni(c.getString(0));
               j.setName(c.getString(1));
               j.setSurname(c.getString(2));
               j.setSaldo(c.getInt(3));
            }
        }
        if(error){
            errorMensaje.setText("Error, DNI incorrecto");
        } else{
            saioaText.setText("ONGI ETORRI \n"+ j.getName().toUpperCase());
            errorMensaje.setText("");
            jolastuButton.setVisibility(View.VISIBLE);
            editDni.setVisibility(View.INVISIBLE);
            loginText.setVisibility(View.INVISIBLE);
            cerrarSesion.setVisibility(View.VISIBLE);
            userFoto.setVisibility(View.VISIBLE);
            cutreCoinCantidad.setVisibility(View.VISIBLE);
            cutreCoin.setVisibility(View.VISIBLE);
            cutreCoinCantidad.setText(j.getSaldo()+"");
            Toast.makeText(this, "Ongi Etorri "+j.getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cerrarSesion(View v){
        saioaText.setText("Saioa Hasi");
        errorMensaje.setText("");
        jolastuButton.setVisibility(View.INVISIBLE);
        editDni.setText("");
        editDni.setVisibility(View.VISIBLE);
        loginText.setVisibility(View.VISIBLE);
        cerrarSesion.setVisibility(View.INVISIBLE);
        userFoto.setVisibility(View.INVISIBLE);
        cutreCoin.setVisibility(View.INVISIBLE);
        cutreCoinCantidad.setVisibility(View.INVISIBLE);
        cutreCoinCantidad.setText("");
        Toast.makeText(this, "Agur "+j.getName(), Toast.LENGTH_SHORT).show();

    }

}