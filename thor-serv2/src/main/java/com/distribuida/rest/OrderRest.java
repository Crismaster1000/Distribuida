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

import com.distribuida.entidades.Orders;
import com.distribuida.services.OrderService;

@Path("/orders")
@ApplicationScoped
public class OrderRest {
	
	@Inject
	OrderService servicio;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Orders> buscarOrdenes() throws SQLException {
		return servicio.listaOrdersCustomers();
		//return servicio.listar();
	}
	
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response buscarOrdenId( @PathParam("id") Integer id ) {
		Orders ord = servicio.buscarId(id);
		if(ord != null) {
			//return Response.ok(ord).build();
			return Response.status(Response.Status.OK).entity(ord).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("Persona No Encontrada").build();

		}
		
	}
	
	
	/*@GET @Path("customer/{id}")
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response buscarOrdenesbyCustomer( @PathParam("id") Long id ) {
		List<Orders> orders = servicio.buscarCustomerId(id);
		if (id != null) {
            return Response.ok(orders).build();
        }
        return Response.noContent().build();
	}*/
	
	
	@POST
	@Produces(value=MediaType.APPLICATION_JSON)
	@Consumes(value=MediaType.APPLICATION_JSON)
	public Response crearOrden(Orders ord) {
		//servicio.crear(ord);
		//return Response.ok().build();
		if (ord != null) {

			servicio.crear(ord);
			return Response.status(Response.Status.OK).entity("Insertado con exito").build();

		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se pudo insertar").build();

		}
	}
	
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON) 
	@Consumes(value=MediaType.APPLICATION_JSON) 
	public Response actualizarOrden( Orders order) {
		
		if (order != null) {
			servicio.update(order);
			return Response.status(Response.Status.OK).entity("Actualizado con exito").build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).entity("No se puede actualizar").build();

		}
	}
	
	
	@DELETE 
	@Path("/{id}")
	public Response eliminarOrden( @PathParam("id") Integer id) throws SQLException {
		// ELIMINA UNA ORDEN SEGUN EL ID
		servicio.delete(id);
		return Response.noContent().build();
	}

}
