package com.distribuida.entidad;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    //@NamedQuery(name = "Persona.findById", query = "Persona p FROM Persona p WHERE p.id = :id")
})
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	public Integer id;
	public String cedula;
	public String direccion;
	public Integer tipodireccion;
	public String nombre;
	@Transient
	public String descripcion;
	
	public Persona() {
		
	}
	
	public Persona(Integer id, String cedula, String direccion, Integer tipodireccion, String nombre) {
		this.id = id;
		this.cedula = cedula;
		this.direccion = direccion;
		this.tipodireccion = tipodireccion;
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getTipodireccion() {
		return tipodireccion;
	}

	public void setTipodireccion(Integer tipodireccion) {
		this.tipodireccion = tipodireccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
