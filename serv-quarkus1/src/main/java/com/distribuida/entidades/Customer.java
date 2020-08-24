package com.distribuida.entidades;

import javax.persistence.Entity;


import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Customer extends PanacheEntity{
	
	
	//private Long id;
	public String name;
	public String surname;
	
	public Customer() {
		
	}
	
	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}



	/*public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	
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

	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + "]";
	}
	
	
	
	

}
