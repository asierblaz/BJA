package eus.uni.dam.model;

import java.util.List;

import org.bson.types.ObjectId;

public class Umea {
	private ObjectId id;
    private String izena;
    private List<String> opariak;
    
	public Umea(ObjectId id, String izena, List<String> opariak) {
		super();
		this.id = id;
		this.izena = izena;
		this.opariak = opariak;
	}
    
	public Umea() {

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

	public List<String> getOpariak() {
		return opariak;
	}

	public void setOpariak(List<String> opariak) {
		this.opariak = opariak;
	}

	@Override
	public String toString() {
		return "Umea [id=" + id + ", izena=" + izena + ", opariak=" + opariak + "]";
	}
    
	
    
}
