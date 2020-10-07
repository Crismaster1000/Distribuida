package com.distribuida.rest;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import javax.ws.rs.GET;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.distribuida.entidad.Direccion;
import com.distribuida.entidad.Persona;
import com.distribuida.proxy.DireccionInter;
import com.distribuida.servicios.PersonaService;


@Path("/persona")  
@ApplicationScoped
public class PersonaRest {
	
	@Inject
	PersonaService servicio;
	
	/*@Inject
    @RestClient
    DireccionInter service;*/
	
	
	@GET
	@Path("/direcciones")
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Persona> ListarDirecciones() throws SQLException {
		return servicio.listaPersonas();
		//return servicio.buscarDir();
	}
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public List<Persona> ListarPersonas() throws SQLException {
		
		return servicio.buscar();
	}
	
	

}
