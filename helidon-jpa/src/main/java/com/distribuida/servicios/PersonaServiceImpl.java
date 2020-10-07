package com.distribuida.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.distribuida.entidad.Direccion;
import com.distribuida.entidad.Persona;
import com.distribuida.proxy.DireccionProxyImpl;



@ApplicationScoped
@Transactional
public class PersonaServiceImpl implements PersonaService {
	
	@PersistenceContext 
	private EntityManager em;
	
	@Inject
	private DireccionProxyImpl servicioDir;
	
	
	@Override
	public List<Persona> buscar() {
				
		List<Persona> lista = null;
		lista = em.createNamedQuery("Persona.findAll", Persona.class).getResultList();
		
		return lista;
		
	}
	
	@Override
	public List<Persona> listaPersonas() throws SQLException{
		
		List<Direccion> listaDireccion = servicioDir.ListarDirecciones();
		List<Persona> listaPer = buscar();
		List<Persona> lista = new ArrayList<>();
		
		for (Persona per : listaPer) {
			for (Direccion dir : listaDireccion) {
				if(per.id == dir.id) {
					per.descripcion = dir.descripcion;
					lista.add(per);
				}
				
			}
			
		}
		
		return lista;
		
		
	}

	

}
