package com.example.kafeosasungarria;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Telephony;
import android.util.JsonWriter;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;


public class DataConnect extends Thread {

    final public static String url = "jdbc:postgresql://192.168.65.48:5432/BJA";
    final public static String user = "jon";
    final public static String pass = "admin";
    ArrayList<Jokalaria> jokalariak = new ArrayList<>();
    private Connection connection;
    private boolean status;


    private Context context;

    public DataConnect(Context context) {
        this.context = context;
    }


    public void connect2() {
        Thread thread = new Thread(new Runnable() {
            //            Datu basera konektatu
            @Override
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("Konektatuta:" + status);
                } catch (Exception e) {
                    status = false;
                    System.out.print("Error :(");
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    //Konexio funtzioa
    public Connection Connect() throws SQLException, ClassNotFoundException {
        //Konexioa egiten saiatzen da
        try {
            //PostgreSQL-ren driver-a
            Class.forName("org.postgresql.Driver");
            /*
            Helbidea, erabiltzailea eta pasahitzaren bitartez konexioa egin eta bueltatzen da
            url   =>  jdbc:postgresql://192.168.65.15:5432/PatitosdeGoma
            user  =>  openpg
            pass  =>  openpgpwd
            */
            Connection conn = DriverManager.getConnection(url, user, pass);
            status = true;
            Log.d("correcto", "Online");

            return conn;
            //SQL salbuespena
        } catch (SQLException se) {
            Log.d("SQLException", "No se puede conectar :(. Error: " + se.toString());

            this.status = false;
            //Driver sabuespena
        } catch (ClassNotFoundException e) {
            Log.d("ClassNotFoundException", "No se encuentra la clase. Error: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Partida> getPartidas() {
        SQLiteDatabase db = context.openOrCreateDatabase("BJA", context.MODE_PRIVATE, null);
        ArrayList<Partida> partidas = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM partida", null);
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                String fecha = c.getString(3);
                Date date = new Date();
                try {
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter1.parse(fecha);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Cursor c2 = db.rawQuery("SELECT name,saldo FROM jugador WHERE dni = '" + c.getString(1) + "'", null);
                while (c2.moveToNext()) {
                    Jokalaria j = new Jokalaria();
                    j.setDni(c.getString(1));
                    j.setName(c2.getString(0));
                    j.setSaldo(c2.getInt(1));
                    Partida p = new Partida(c.getInt(0), c.getInt(2), j, date);
                    partidas.add(p);
                }
            }
        }
        return partidas;
    }

    public void jokalariakToSqlite() {
        Thread thread = new Thread(new Runnable() {
            //            Datu basera konektatu
            @Override
            public void run() {
                try {

                    //Query-a
                    String query = "SELECT identification_id, name, x_saldo FROM hr_employee";
                    //Connect() funtzioari deitzen zaio konexioa gordetzeko
                    Connection conn = Connect();
                    connect2();
                    if (isStatus()) {
                        //Query-a gorde eta exekutatzen da
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        //Hemen bueltatutako bezero guztiak
                        while (rs.next()) {
                            Jokalaria j = new Jokalaria();
                            j.setDni(rs.getString(1));
                            j.setName(rs.getString(2));
                            j.setSurname("");
                            j.setSaldo(rs.getInt(3));
                            jokalariak.add(j);
                        }

                        //cargo los jugadores en sqlite
                        SQLiteDatabase db = context.openOrCreateDatabase("BJA", context.MODE_PRIVATE, null);
                        db.execSQL("DELETE FROM jugador");

                        for (Jokalaria j : jokalariak) {
                            db.execSQL("INSERT INTO jugador(dni, name, saldo)  VALUES ('" + j.getDni() + "', '" + j.getName() + "', '" + j.getSaldo() + "')");

                        }

                    }
                    //Konexioa ixten da
                    conn.close();
                    //Salbuespena
                } catch (Exception e) {
                    Log.d("Exception", "run: Failed " + e.getMessage());
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }

    public void partidakToPostgre() {
        Thread thread = new Thread(new Runnable() {
            //            Datu basera konektatu
            @Override
            public void run() {
                try {
                    //Query-a
                    ArrayList<Partida> partidas = getPartidas();
                    //Connect() funtzioari deitzen zaio konexioa gordetzeko

                    Connection conn = Connect();
                    //Query-a gorde eta exekutatzen da
                    Statement st = conn.createStatement();

                    for (Partida p : partidas) {

                        String sql = "INSERT INTO lehiaketa_puntuazioa (name, puntuak, data) "
                                + "VALUES ('" + p.getJokalaria().getName() + "', '" + p.getPuntuacion() + "', '" + p.getFecha() + "');";
                        st.executeUpdate(sql);


                        String sql2 = "UPDATE hr_employee set x_saldo='" + p.getJokalaria().getSaldo() + "' WHERE identification_id='" + p.getJokalaria().getDni() + "'";
                        st.executeUpdate(sql2);


                    }
                    SQLiteDatabase db = context.openOrCreateDatabase("BJA", context.MODE_PRIVATE, null);
                    db.execSQL("DELETE FROM partida");

                    //Konexioa ixten da
                    conn.close();


                    //Salbuespena
                } catch (Exception e) {
                    Log.d("Exception", "run: Failed " + e.getMessage());
                }

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
            this.status = false;
        }
    }


    public boolean isStatus() {
        return status;
    }


  /*  public ArrayList<String> json() throws JSONException {

        ArrayList<String> a = new ArrayList<>();


        int i=0;
        for (Partida p : getPartidas()) {
            JSONObject datos = new JSONObject();
            datos.put("id",i++);
            JSONObject jokalariObj = new JSONObject();

            jokalariObj.put("dni", p.getJokalaria().getDni());
            jokalariObj.put("name", p.getJokalaria().getName());
            jokalariObj.put("surname", p.getJokalaria().getSurname());
            jokalariObj.put("saldo", p.getJokalaria().getSaldo());


            JSONObject partidaObj = new JSONObject();
            partidaObj.put("puntuacion", p.getPuntuacion());
            partidaObj.put("fecha", p.printFecha());
            partidaObj.put("jokalaria", jokalariObj);

            datos.put("partida",partidaObj);

            a.add(datos.toString());
        }


        return a;

    }*/

    public String datosJson() throws JSONException {
        String s="[";
        String separador=",";
        int i=0;
        for (Partida p : getPartidas()) {
            JSONObject datos = new JSONObject();
            JSONObject jokalariObj = new JSONObject();
            jokalariObj.put("dni", p.getJokalaria().getDni());
            jokalariObj.put("name", p.getJokalaria().getName());
            jokalariObj.put("surname", p.getJokalaria().getSurname());
            jokalariObj.put("saldo", p.getJokalaria().getSaldo());


            JSONObject partidaObj = new JSONObject();
            partidaObj.put("puntuacion", p.getPuntuacion());
            partidaObj.put("fecha", p.printFecha());
            partidaObj.put("jokalaria", jokalariObj);

            datos.put("partida",partidaObj);


            if(i!=getPartidas().size()-1) {
                s = s + datos.toString() + separador;
            }else{
                s = s + datos.toString()+"]";
            }
            i++;

        }
        return s;


    }

 public void  enviarDatos(){

     try{
         Socket socketZerbitzareakin = new Socket("192.168.65.8", 12345);  	// Zerbitzariarekin konektatzen saiatuko naiz
         OutputStream os = socketZerbitzareakin.getOutputStream();		// Zerbitzariari idazteko zabaldutako OutputStream
         DataOutputStream dout = new DataOutputStream(os);  				// OutputStream-arekin lan egiteko objektu bat
         //	String bezeroaren_mezua = "que pasa";

         dout.writeUTF(datosJson());  								// Zerbitzariari bidalitako mezua
         dout.flush();
         dout.close();
         os.close();
         socketZerbitzareakin.close();
     }catch(Exception e)
     {
         System.out.println(e);
     }

 }

    public void connectSocket(){
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socketZerbitzareakin = new Socket("192.168.65.8", 12345);  	// Zerbitzariarekin konektatzen saiatuko naiz
                    OutputStream os = socketZerbitzareakin.getOutputStream();		// Zerbitzariari idazteko zabaldutako OutputStream
                    DataOutputStream dout = new DataOutputStream(os);  				// OutputStream-arekin lan egiteko objektu bat

                    dout.writeUTF(datosJson());  								// Zerbitzariari bidalitako mezua
                    dout.flush();
                    dout.close();
                    os.close();
                    socketZerbitzareakin.close();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        t2.start();
    }


}
