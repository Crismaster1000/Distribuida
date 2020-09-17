package com.distribuida.proxy;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.distribuida.datos.Orders;


public interface proxyOrders {
	@GET
	@Consumes(value = MediaType.APPLICATION_JSON)
	List<Orders> listarTodos();
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	Response crearPersona(Orders orders);
	
	@PUT
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	Response editarPersona(Orders orders);
	
	@DELETE
	@Path("/{id}")
	void eliminarPersona(@PathParam("id") Long id);
	

	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	Response buscar(@PathParam("id") Long id);
}
