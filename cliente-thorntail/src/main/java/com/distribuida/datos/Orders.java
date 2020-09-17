package com.distribuida.datos;

import java.io.Serializable;




public class Orders implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Long id;
	 
	public String item;
	public double price;
	public Long customer_id;


	public String nombreCustomer;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getItem() {
		return item;
	}


	public void setItem(String item) {
		this.item = item;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Long getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}


	public String getNombreCustomer() {
		return nombreCustomer;
	}


	public void setNombreCustomer(String nombreCustomer) {
		this.nombreCustomer = nombreCustomer;
	}
	


	
	
	
}
