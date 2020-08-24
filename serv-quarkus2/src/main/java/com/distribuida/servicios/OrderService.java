package com.distribuida.servicios;

import java.util.List;

import com.distribuida.entidades.Orders;


public interface OrderService {
	
	
	public Orders crear(Orders ord);
	
	public Orders buscarId(Long id);
	
	public Orders update(Orders ord);
	
	public void delete(Long id);
	
	public List<Orders> buscarCustomerId(Long id);

}
