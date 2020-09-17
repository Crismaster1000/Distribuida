package com.distribuida.services;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import com.distribuida.entidades.Customer;


public interface CustomerService {
	
	public List<Customer> listar() throws SQLException;
	
	public Customer buscarId(Integer id);
	
	@Transactional
	public void crear(Customer cm);
		
	@Transactional
	public void update(Customer cus);
	
	@Transactional
	public void delete(Integer id);

}
