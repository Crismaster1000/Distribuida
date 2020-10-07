package com.distribuida.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name ="Direccion.findAll", query = "SELECT d FROM Direccion d")
    //@NamedQuery(name = "Persona.findById", query = "Persona p FROM Persona p WHERE p.id = :id")
})
public class Direccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	public Integer id;
	public String descripcion;
	
	public Direccion() {
		
	}
	
	public Direccion(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
