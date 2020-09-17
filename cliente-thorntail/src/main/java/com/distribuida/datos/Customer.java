package com.distribuida.datos;

import java.io.Serializable;




public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	

	public Long id;
	
	public String name;
	public String surname;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	


}
