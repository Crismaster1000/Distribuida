package com.distribuida.servicios;

import com.distribuida.entidades.Customer;


public interface CustomerService {
	
	public Customer crear(Customer cm);
		
	public Customer buscarId(Long id);
	
	public Customer update(Customer cm);
	
	public void delete(Long id);

}
