package com.distribuida.rest;

import java.sql.SQLException;
import java.util.List;

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
import com.distribuida.servicios.CustomerService;



@Path("/customers")
public class CustomerRest {
	
	@Inject
	CustomerService servicio;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Customer> buscarCustomers() {
		
		return Customer.listAll();
	}
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que devolveremos un JSON
	public Response buscarCustomerId( @PathParam("id") Long id ) {
		// BUSCA UNA PERSONA SEGUN EL ID
		Customer cm = servicio.buscarId(id);
		if(cm != null) 
			return Response.ok(cm).build();
		
		return Response.noContent().build();
		
		/*Customer cm = new Customer();
		return cm.findById(id);*/
		
	}
	
	
	@POST
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response crearCustomer(Customer cm) {
		cm = servicio.crear(cm);
		return Response.ok(cm).build();
		
		/*Customer newcm = new Customer();
		newcm.setName(cm.getName());
		newcm.setSurname(cm.getSurname());
		newcm.persist();*/
		
	}
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	@Consumes(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	public Response actualizarCustomer( Customer customer) {
		customer = servicio.update(customer);
		return Response.ok(customer).build();
		
	}
	
	@DELETE 
	@Path("/{id}")
	public Response eliminarCustomer( @PathParam("id") Long id) throws SQLException {
		// ELIMINA UNA PERSONA SEGUN EL ID
		servicio.delete(id);
		return Response.noContent().build();
		
		
	}
	

}
