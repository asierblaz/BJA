package com.example.kafeosasungarria;

import java.io.Serializable;

public class Jokalaria implements Serializable {

   private String dni;
    private String name;
    private String surname;
    private String departamentua;
    private int saldo;
    private int adina;


    public Jokalaria() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartamentua() {
        return departamentua;
    }

    public void setDepartamentua(String departamentua) {
        this.departamentua = departamentua;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getAdina() {
        return adina;
    }

    public void setAdina(int adina) {
        this.adina = adina;
    }
}
