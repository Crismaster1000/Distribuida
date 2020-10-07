package com.distribuida.servicios;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import com.distribuida.entidad.Direccion;
import com.distribuida.entidad.Persona;


public interface PersonaService {
	
	public List<Persona> buscar();
	
	/*
	@Transactional
	Persona buscar_id(Integer id);
	
	@Transactional
	public void crear(Persona per);
		
	@Transactional
	public void update(Persona per);
	
	@Transactional
	public void delete(Integer id);*/
	
	public List<Persona> listaPersonas() throws SQLException;

}
