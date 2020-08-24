package com.distribuida.restfull;

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

import com.distribuida.objetos.Persona;
import com.distribuida.servicios.PersonaServ;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/persona")  // http://127.0.0.1:8080/rest02/persona
@ApplicationScoped
public class PersonaRest {
	
	@Inject
	private PersonaServ servicio;
	
	@GET
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que devolveremos un JSON
	public List<Persona> listar( ) {
		// DEVUELVE TODAS LAS PERSONAS DE LA BASE
		return servicio.buscar();
	}
	
	
	@GET @Path("/{id}")
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que devolveremos un JSON
	public Response buscar( @PathParam("id") Integer id ) {
		// BUSCA UNA PERSONA SEGUN EL ID
		Persona per =  servicio.buscar_id(id );
		
		if (per != null) {
			return Response
					.status( Response.Status.OK)
					.entity( per )
					.build();
		}
		else {
			return Response
					.status( Response.Status.NOT_FOUND )
					.entity( "PERSONA NO ENCONTRADA ")
					.build();
		}
	}
	
	
	@POST
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	@Consumes(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	public void insertar( String persona ) throws SQLException {
		// INSERTA UNA PERSONA EN LA BASE DE DATOS
		
		Persona per = new Persona();
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			per = mapper.readValue( persona , Persona.class);
			servicio.insertar(per);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "ERROR AL CREAR" );
		}
	     
	}
	
	@PUT
	@Produces(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	@Consumes(value=MediaType.APPLICATION_JSON) //indicamos que aceptamos un JSON
	public void actualizar( String persona) {
		
		Persona per = new Persona();

		ObjectMapper mapper = new ObjectMapper();
		try {
			per = mapper.readValue( persona , Persona.class);
			servicio.actualizar(per);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println( "ERROR AL CREAR" );
		}
		
		
	}
	
	
	@DELETE @Path("/{id}")
	public void eliminar( @PathParam("id") Integer id) throws SQLException {
		// ELIMINA UNA PERSONA SEGUN EL ID
		servicio.borrar(id);
	}
	
	

}
