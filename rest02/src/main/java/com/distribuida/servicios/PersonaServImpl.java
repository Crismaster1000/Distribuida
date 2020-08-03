package com.distribuida.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.distribuida.objetos.Persona;

@ApplicationScoped
public class PersonaServImpl implements PersonaServ {
	
	@Inject
	private DataSource dataSource;
	
	//private static String xx = "Persona";
	
	private static final String SELECT_ALL_USERS = "select * from persona";
    private static final String DELETE_USERS_SQL = "delete from Persona where id = ?;";
    private static final String UPDATE_USERS_SQL = "update Persona set nombre = ?,direccion = ?, correo =? where id = ?;";
    private static final String INSERT_USERS_SQL = "INSERT INTO Persona" + "  (id,nombre,direccion,correo) VALUES " +
            " (?, ?, ?, ?);";
    
    @Override
    public Persona buscar_id(int id) {
		// TODO Auto-generated method stub
    	Persona persona = null;
    	String query = "select * from persona where id=" + id +";";
    
    	try ( Connection conn = dataSource.getConnection(); 
				PreparedStatement preparedStatement = conn.prepareStatement(query);){
			
			ResultSet rs = preparedStatement.executeQuery(); //ejecutamos el query y lo almacenamos en un ResultSet
			
			// Pasamos los resultados a la lista
            while (rs.next()) {
                int id1 = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                persona = new Persona(id1, nombre, direccion, correo);
            }
            
            conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return persona;
	}

	@Override
	public List <Persona> buscar() {
		// TODO Auto-generated method stub
		// creamos una lista de tipo Persona donde estaran los datos consultados 
		List <Persona> persona = new ArrayList<>();
		
		//creamos la conexion con la base de datos y creamos un prepareStatement
		try ( Connection conn = dataSource.getConnection(); 
				PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_USERS);){
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery(); //ejecutamos el query y lo almacenamos en un ResultSet
			
			// Pasamos los resultados a la lista
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");
                String correo = rs.getString("correo");
                persona.add(new Persona(id, nombre, direccion, correo));
            }
            
            conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// retornamos la lista de personas
		return persona;
		
	}
	
	@Override
	public void insertar(Persona op) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(INSERT_USERS_SQL);
		
		try ( Connection conn = dataSource.getConnection(); 
				PreparedStatement preparedStatement = conn.prepareStatement(INSERT_USERS_SQL);){
			
			preparedStatement.setInt(1, op.getId());
			preparedStatement.setString(2, op.getNombre());
            preparedStatement.setString(3, op.getDireccion());
            preparedStatement.setString(4, op.getCorreo());
            
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean actualizar(Persona op) throws SQLException {
		// TODO Auto-generated method stub
		boolean actualizado;
		
		try ( Connection conn = dataSource.getConnection(); 
				PreparedStatement statement = conn.prepareStatement(UPDATE_USERS_SQL);){
			
			statement.setString(1, op.getNombre());
            statement.setString(2, op.getDireccion());
            statement.setString(3, op.getCorreo());
            statement.setInt(4, op.getId());

            actualizado = statement.executeUpdate() > 0;
			
		}
		
		return actualizado;
		
	}

	@Override
	public boolean borrar(int id) throws SQLException { //devuelve un boolean cuando borra un registro
		// TODO Auto-generated method stub
		boolean borrado;
		try ( Connection conn = dataSource.getConnection(); 
				PreparedStatement statement = conn.prepareStatement(DELETE_USERS_SQL);){
			
			statement.setInt(1, id);
			borrado = statement.executeUpdate() >0; 
			
		}
		return borrado;
		
	}

	

}
