package com.distribuida.services;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;


import com.distribuida.entidades.Orders;


public interface OrderService {
	
	public List<Orders> listar() throws SQLException;
	
	public Orders buscarId(Integer id);
	
	@Transactional
	public void crear(Orders ord);
		
	@Transactional
	public void update(Orders ord);
	
	@Transactional
	public void delete(Integer id);
	
	public List<Orders> listaOrdersCustomers() throws SQLException;
	
	//public List<Orders> buscarCustomerId(Long id);

}
