package com.example.kafeosasungarria;

import java.io.Serializable;

public class Partida implements Serializable {

    private int puntuacion;
    private  Jokalaria jokalaria;


    public Partida(Jokalaria j) {
        this.jokalaria = j;
    }

    public Partida() {
    }

    public Partida(int id, int puntuacion, Jokalaria j) {
        this.puntuacion = puntuacion;
        this.jokalaria = j;
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

    public void setJokalaria(Jokalaria jokalaria) {
        this.jokalaria = jokalaria;
    }

    @Override
    public String toString() {
        return "Partida{" +
                ", puntuacion=" + puntuacion +
                ", j=" + jokalaria +
                '}';
    }
}
