package com.distribuida.servicios;


import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.distribuida.objetos.Persona;

import io.helidon.common.reactive.Multi;
import io.helidon.common.reactive.Single;

import io.helidon.dbclient.DbClient;

@ApplicationScoped
public class PersonaServImpl implements PersonaServ {
	
	@Inject
	private DbClient dbclient;
	
    @Override
    public Persona buscar_id(int id) {
		// TODO Auto-generated method stub
    	Persona persona = new Persona();
    	
    	try{
    		Multi<Persona> listaper= dbclient
    				.execute(exec -> exec
    				.namedQuery("select-id", id)
    				.map(it ->	{
    				Persona p = new Persona();
					
					p.setId( it.column("id").as(Integer.class));
					p.setNombre(it.column("nombre").as(String.class));
					p.setDireccion(it.column("direccion").as(String.class));
					p.setCorreo(it.column("correo").as(String.class));
					
					return p;
    				}
    				));
    				
    	Single<List<Persona>> per = listaper.collectList();
    	List<Persona> listaun = per.get();
    	persona = listaun.get(0);
    	} catch(Exception e) {
    		e.printStackTrace();
    		return persona;
    	}
    	
    	return persona;
    	
    	
	}

	@Override
	public List <Persona> buscar() {
		// TODO Auto-generated method stub
		Multi<Persona> rows = dbclient.execute(exec -> exec
				.namedQuery("select-persona")
				.map(it ->	{
					Persona p = new Persona();
					
					p.setId( it.column("id").as(Integer.class));
					p.setNombre(it.column("nombre").as(String.class));
					p.setDireccion(it.column("direccion").as(String.class));
					p.setCorreo(it.column("correo").as(String.class));
					
					return p;
				} 
				));
		
		Single<List<Persona>> personalist = rows.collectList();
		
		try {
		List <Persona> persona = personalist.get();
		return persona;
		} catch (ExecutionException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	@Override
	public void insertar(Persona op) throws SQLException {
		// TODO Auto-generated method stub
		//System.out.println(INSERT_USERS_SQL);
		dbclient.execute(exec -> exec 
				.createNamedInsert("insert-persona")
				.addParam(op.getId()).addParam(op.getNombre()).addParam(op.getDireccion()).addParam(op.getCorreo())
				.execute());
				
		
	}

	@Override
	public boolean actualizar(Persona op) throws SQLException {
		// TODO Auto-generated method stub
		boolean actualizado;
		String updatestm = "UPDATE persona SET nombre = '" + op.getNombre() +"',"
				+ "direccion = '" +op.getDireccion()+ "',"
				+ "correo = '" + op.getCorreo() +"',"
				+ "where id = " +op.getId();
		
		dbclient.execute(exec -> exec.createUpdate(updatestm).execute()).thenAccept(count -> System.out.printf("actualizado",count));
				//.update(statement, parameters)
				//.createNamedUpdate("update-persona")
				//.namedQuery("update-persona",id,nombre,direccion,correo).thenAccept(count -> System.out.printf("borrado",count);
				//.addParam(op.getId()).addParam(op.getNombre()).addParam(op.getDireccion()).addParam(op.getCorreo())
				//.execute());
		
		actualizado = true;
		return actualizado;
		
	}

	@Override
	public boolean borrar(Integer id) throws SQLException { //devuelve un boolean cuando borra un registro
		// TODO Auto-generated method stub
		boolean borrado;
		
		dbclient.execute(exec -> exec 
				.delete("delete from Persona where id = ?",id))
				.thenAccept(count -> System.out.printf("borrado",count));
				//.createNamedQuery("delete-persona","delete from Persona where id = ?;")
				//.addParam(id)
				//.execute());
		
		borrado = true;
		return borrado;
		
	}

	

}
