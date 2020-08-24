package com.distribuida.entidades;


public class Orders{
	
	public Long id;
	public String item;
	public Float price;
	public Long customer_id;
	
	public Orders() {
		super();
	}
	
	public Orders(String item, Float price, Long customer_id) {
		this.item = item;
		this.price = price;
		this.customer_id = customer_id;
	}
	
	public Orders(Long id, String item,Float price) {
		this.id = id;
		this.item = item;
		this.price = price;
	}
	
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
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	
	
	
	

}
