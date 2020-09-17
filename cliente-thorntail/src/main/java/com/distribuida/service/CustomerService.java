package com.distribuida.service;

import java.util.List;

import com.distribuida.datos.Customer;


public interface CustomerService {
	
	public List<Customer> listarTodos() ;
	
	public String  crearPersona(Customer customer);
	
	public String editarPersona(Customer customer);
	
	public void eliminarPersona( Long id);
	

	public String buscar( Long id);
}
