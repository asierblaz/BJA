package com.example.kafeosasungarria;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

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
import android.util.Log;


public class DataConnect extends Thread {

    final public static String url = "jdbc:postgresql://192.168.65.48:5432/BJA";
    final public static String user = "jon";
    final public static String pass = "admin";
    ArrayList<Jokalaria> jokalariak= new ArrayList<>();
    private Connection connection;
    private boolean status;


    private  Context context;

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
    public  Connection Connect() throws SQLException, ClassNotFoundException {
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
            Log.d("h","Hola");
            Connection conn = DriverManager.getConnection(url, user, pass);
            status = true;
            Log.d("correcto", "Kaixo estoy online euskaltel fibra 300");

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

    public ArrayList<Partida> getPartidas(){
        SQLiteDatabase db = context.openOrCreateDatabase("BJA", context.MODE_PRIVATE, null);
        ArrayList<Partida> partidas = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM partida", null);
        if(c.getCount() > 0){
            while(c.moveToNext()) {
                String fecha= c.getString(3);
                Date date= new Date();
                try {
                    SimpleDateFormat formatter1=new SimpleDateFormat("dd/MM/yyyy");
                    date=formatter1.parse(fecha);

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Cursor c2 = db.rawQuery("SELECT name FROM jugador WHERE dni = '"+ c.getString(1) +"'", null);
                while(c2.moveToNext()) {
                    Jokalaria jsolodni = new Jokalaria();
                    jsolodni.setDni(c.getString(1));
                    jsolodni.setName(c2.getString(0));
                    Partida p = new Partida(c.getInt(0), c.getInt(2), jsolodni, date);

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
                    if(isStatus()) {
                        //Query-a gorde eta exekutatzen da
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        //Hemen bueltatutako bezero guztiak
                        while (rs.next()) {
                            Jokalaria j = new Jokalaria();
                            j.setDni(rs.getString(1));
                            j.setName(rs.getString(2));
                            j.setSurname("a");
                            j.setSaldo(rs.getInt(3));
                            jokalariak.add(j);
                        }

                        Log.d("Jokalaria", jokalariak.toString());


                        //cargo los jugadores en sqlite
                        SQLiteDatabase db = context.openOrCreateDatabase("BJA", context.MODE_PRIVATE, null);
                        db.execSQL("DELETE FROM jugador");
                        for (int i = 0; i < jokalariak.size(); i++) {
                            db.execSQL("INSERT INTO jugador(dni, name, saldo)  VALUES ('" + jokalariak.get(i).getDni() + "', '" + jokalariak.get(i).getName() + "', '"+ jokalariak.get(i).getSaldo() +"')");
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

                    for (int i=0;i<partidas.size();i++) {
                        String sql = "INSERT INTO lehiaketa_puntuazioa (name, puntuak, data) "
                                + "VALUES ('"+ partidas.get(i).getJokalaria().getName()+"', '"+ partidas.get(i).getPuntuacion() +"', '"+ partidas.get(i).getFecha() +"');";
                        st.executeUpdate(sql);

                    }
                    saldoToPostgre();

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

    public void saldoToPostgre() {
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

                    for (int i=0;i<partidas.size();i++) {
                        String sql = "UPDATE hr_employee SET x_saldo = x_saldo + '"+ partidas.get(i).getPuntuacion() +"' WHERE name = '"+ partidas.get(i).getJokalaria().getName() +"'";
                        st.executeUpdate(sql);

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


}
