package com.distribuida.proxy;


import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.distribuida.datos.Customer;



public interface proxyCustomer {
	@GET
	@Consumes(value = MediaType.APPLICATION_JSON)
	List<Customer> listarTodos() ;
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	Response crearPersona(Customer customer);
	
	@PUT
	@Consumes(value = MediaType.APPLICATION_JSON)
	@Produces(value = MediaType.APPLICATION_JSON)
	Response editarPersona(Customer customer);
	
	@DELETE
	@Path("/{id}")
	void eliminarPersona(@PathParam("id") Long id);
	

	@GET
	@Path("/{id}")
	@Produces(value = MediaType.APPLICATION_JSON)
	Response buscar(@PathParam("id") Long id);
}
