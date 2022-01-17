package eus.uni.dam.model;

import java.util.Date;

public class Partida {

	private int id;
	private int puntuazioa;
	private Date fecha;
	private Jokalaria jokalaria;
	
	
	public Partida() {
	}



	public Partida(int id, int puntuazioa, Date fecha, Jokalaria jokalaria) {
		this.id = id;
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



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
		return "Partida [id=" + id + ", puntuazioa=" + puntuazioa + ", fecha=" + fecha + ", jokalaria=" + jokalaria
				+ "]";
	}



	
	
	
	
}
