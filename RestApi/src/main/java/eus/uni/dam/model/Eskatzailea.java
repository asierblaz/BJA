package eus.uni.dam.model;

import java.util.List;

import org.bson.types.ObjectId;

import eus.uni.dam.model.Oparia;

public class Eskatzailea {
	private ObjectId id;
    private String izena;
    private List<Oparia> opariak;
    
    public Eskatzailea() {
    	super();
    }
    
    
	public Eskatzailea(ObjectId id, String izena, List<Oparia> opariak) {
		super();
		this.id = id;
		this.izena = izena;
		this.opariak = opariak;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public List<Oparia> getOpariak() {
		return opariak;
	}
	public void setOpariak(List<Oparia> opariak) {
		this.opariak = opariak;
	}
    
    
}
