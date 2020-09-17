package com.distribuida.rest;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.distribuida.entidades.Customer;
import com.distribuida.services.CustomerService;

@Path("/customer")
@ApplicationScoped
public class CustomerRest {
	
	@Inject
	CustomerService servicio;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Customer> ListarCustomers() throws SQLException {
		
		return servicio.listar();
	}
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response buscarCustomerId( @PathParam("id") Integer id ) {
		Customer cm = servicio.buscarId(id);
		if(cm != null) 
			return Response.ok(cm).build();
		return Response.noContent().build();
	}
	
	@POST
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response crearCustomer(Customer cm) {
		servicio.crear(cm);
		return Response.ok().build();
	}
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON) 
	@Consumes(value=MediaType.APPLICATION_JSON) 
	public Response actualizarCustomer( Customer customer) {
		servicio.update(customer);
		return Response.ok().build();
	}
	
	@DELETE 
	@Path("/{id}")
	public Response eliminarCustomer( @PathParam("id") Integer id) throws SQLException {
		servicio.delete(id);
		return Response.noContent().build();
			
	}

}
