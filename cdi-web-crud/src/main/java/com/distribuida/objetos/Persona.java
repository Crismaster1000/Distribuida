package com.distribuida.objetos;

public class Persona {
	
	private int id;
	private String nombre;
	private String direccion;
	private String correo;
	
	public Persona(int id, String nombre, String direccion, String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correo = correo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	

}
