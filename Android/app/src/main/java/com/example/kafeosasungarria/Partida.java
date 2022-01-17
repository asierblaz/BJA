package com.example.kafeosasungarria;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Partida implements Serializable {

    private int puntuacion;
    private  Jokalaria jokalaria;
    private Date fecha;

    public Partida(Jokalaria j) {
        this.jokalaria = j;
    }

    public Partida() {
    }

    public Partida(int id, int puntuacion, Jokalaria j,Date fecha) {
        this.puntuacion = puntuacion;
        this.jokalaria = j;
        this.fecha=fecha;
    }


    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Jokalaria getJokalaria() {
        return jokalaria;
    }

    public String printFecha(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(fecha);

    }

    public void setJokalaria(Jokalaria jokalaria) {
        this.jokalaria = jokalaria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Partida{" +
                ", puntuacion=" + puntuacion +
                ", j=" + jokalaria +
                '}';
    }
}
