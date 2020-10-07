package com.distribuida.rest;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.distribuida.entidades.Direccion;


@Path("/direccion") 
@ApplicationScoped
public class DireccionRest {
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Direccion> buscarCustomers() {
		return Direccion.listAll();
	}
	
	

}
