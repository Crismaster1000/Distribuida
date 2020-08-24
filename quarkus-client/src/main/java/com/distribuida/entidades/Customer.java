package com.distribuida.entidades;

public class Customer{
	
	
	private Long id;
	public String name;
	public String surname;
	
	public Customer() {
		
	}
	
	public Customer(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}
	
	public Customer(Long id,String name, String surname) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
	}



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

	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + "]";
	}
	
	
	
	

}
