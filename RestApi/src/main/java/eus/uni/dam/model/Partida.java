package eus.uni.dam.model;

import java.util.Date;

public class Partida {


	private int puntuazioa;
	private Date fecha;
	private Jokalaria jokalaria;
	
	
	public Partida() {
	}



	public Partida( int puntuazioa, Date fecha, Jokalaria jokalaria) {
		this.puntuazioa = puntuazioa;
		this.fecha = fecha;
		this.jokalaria=jokalaria;
	}



	public Jokalaria getJokalaria() {
		return jokalaria;
	}



	public void setJokalaria(Jokalaria jokalaria) {
		this.jokalaria = jokalaria;
	}





	public int getPuntuazioa() {
		return puntuazioa;
	}



	public void setPuntuazioa(int puntuazioa) {
		this.puntuazioa = puntuazioa;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	@Override
	public String toString() {
		return "Partida [puntuazioa=" + puntuazioa + ", fecha=" + fecha + ", jokalaria=" + jokalaria
				+ "]";
	}
	
	
	
}
