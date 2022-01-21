package eus.uni.dam.model;

public class Jokalaria {

	private String dni;
	private String name;
	private int saldo;

	
	
	public Jokalaria() {
		super();
	}

	public Jokalaria(String dni, String name, int saldo) {
		this.dni = dni;
		this.name = name;
		this.saldo = saldo;
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

	@Override
	public String toString() {
		return "Jokalaria [dni=" + dni + ", name=" + name + ", saldo=" + saldo + "]";
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}
