package com.distribuida.service;

import java.util.List;

import com.distribuida.datos.Orders;



public interface OrderService {
	
	
	List<Orders> listarTodos();
	
	public String crearOrden(Orders orders);
	public String  editarOrden(Orders orders);
	public void eliminarOrden( Long id);
	
	public String  buscar(Long id);

}
