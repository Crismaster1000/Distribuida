package com.distribuida.Persona;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@SessionScoped
@Named(value="controlPersona")
public class PersonaController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject PersonaProxy servicioPersona;
	
	private Persona persona;
	
	private List<Persona> listaPersona;
	
	@PostConstruct
	public void init() {
		persona = new Persona();
	}

	
	
	public Persona getPersona() {
		return persona;
	}



	public void setPersona(Persona persona) {
		this.persona = persona;
	}



	public List<Persona> getListaPersona() {
		return listaPersona;
	}



	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}



	public String listadoPersona() throws SQLException {
		listaPersona = servicioPersona.ListarPersonas();
		return "lista?faces-redirect=true";
	}
	
	
}
