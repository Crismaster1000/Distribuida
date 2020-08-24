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

import com.distribuida.entidades.Orders;
import com.distribuida.servicios.OrderService;

@Path("/orders")
public class OrderRest {
	
	@Inject
	OrderService servicio;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Orders> buscarOrdenes() {
		
		return Orders.listAll();
	}
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que devolveremos un JSON
	public Response buscarOrdenId( @PathParam("id") Long id ) {
		// BUSCA UNA PERSONA SEGUN EL ID
		Orders ord = servicio.buscarId(id);
		if(ord != null) 
			return Response.ok(ord).build();
		
		return Response.noContent().build();
		
		
	}
	
	@GET @Path("customer/{id}")
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que devolveremos un JSON
	public Response buscarOrdenesbyCustomer( @PathParam("id") Long id ) {
		List<Orders> orders = servicio.buscarCustomerId(id);
		if (id != null) {
            return Response.ok(orders).build();
        }
        return Response.noContent().build();
	}
	
	
	@POST
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response crearOrden(Orders ord) {
		ord = servicio.crear(ord);
		return Response.ok(ord).build();
		
		
	}
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	@Consumes(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	public Response actualizarOrden( Orders order) {
		order = servicio.update(order);
		return Response.ok(order).build();
		
	}
	
	@DELETE 
	@Path("/{id}")
	public Response eliminarOrden( @PathParam("id") Long id) throws SQLException {
		// ELIMINA UNA PERSONA SEGUN EL ID
		servicio.delete(id);
		return Response.noContent().build();
		
		
	}

}
