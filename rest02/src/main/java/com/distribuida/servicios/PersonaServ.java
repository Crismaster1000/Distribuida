package com.distribuida.servicios;

import java.sql.SQLException;
import java.util.List;

import com.distribuida.objetos.Persona;

// INTERFAZ CON LOS METODOS QUE TENDRA EL END-POINT
public interface PersonaServ {
	
	public List < Persona > buscar();
	
	void insertar(Persona op) throws SQLException;
	
	public boolean actualizar(Persona op) throws SQLException;
	
	public boolean borrar(int id) throws SQLException;
	
	Persona buscar_id(int id);

}
