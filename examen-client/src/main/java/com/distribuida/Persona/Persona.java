package com.distribuida.Persona;

public class Persona {

	public Long id;
	public String cedula;
	public String direccion;
	public String tipodireccion;
	public String nombre;
	public String descripcion;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTipodireccion() {
		return tipodireccion;
	}
	public void setTipodireccion(String tipodireccion) {
		this.tipodireccion = tipodireccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
