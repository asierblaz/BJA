package eus.uni.dam.model;

public class Jokalaria {

	private String dni;
	private String name;
	private String surname;
	private int saldo;

	
	
	public Jokalaria() {
		super();
	}

	public Jokalaria(String dni, String name, String surname, int saldo) {
		this.dni = dni;
		this.name = name;
		this.surname = surname;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

}
