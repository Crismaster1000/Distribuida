package com.restclient.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restclient.objetos.Persona;



@WebServlet(urlPatterns = "/")
public class PersonaCliente extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String URL = "https://erazo-distribuida02.herokuapp.com/persona";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		try {
			switch (action) {
			case "/":
				selectUser(req, resp);
				break;
			case "/consultar":
				selectUser(req, resp);
				break;
			case "/insertar":
				insertUser(req, resp);
				break;
			case "/actualizar":
				updateUser(req, resp);
				break;
			case "/borrar":
				deleteUser(req, resp);
				break;
			case "/buscarID":
				buscar_id(req, resp);
				break;
			default:
				selectUser(req, resp);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
		
	}
	
	private void selectUser(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {

		// HACEMOS UN GET AL SERVICIO PARA QUE NOS RETORNE LA LISTA DE PERSONAS
		URL url = new URL("https://erazo-distribuida02.herokuapp.com/persona");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod( "GET" );
		connection.setRequestProperty( "Accept", "application/json" );
		

		int httpCode = connection.getResponseCode();

		if(httpCode != HttpURLConnection.HTTP_OK) {
			System.err.println(" Error " + httpCode );
			return;
		}

		//commons-io
		//http 200: ok
		InputStream is = connection.getInputStream();

		String json = IOUtils.toString(  is , Charset.defaultCharset() );
		System.out.println( " datos devueltos:  " + json);
		
		// CONVERTIMOS EL JSON A LISTA
		ObjectMapper mapper = new ObjectMapper();
		List<Persona> personalist = mapper.readValue(json, new TypeReference<List<Persona>>(){});
		//System.out.println(personalist.get(1).getNombre());


		for(int i=0 ; i< personalist.size(); i++) {

			System.out.println(personalist.get(i).getId());

			System.out.println(personalist.get(i).getNombre());

			System.out.println(personalist.get(i).getDireccion());

			System.out.println(personalist.get(i).getCorreo());


		}

		req.setAttribute("listUser", personalist);
		req.getRequestDispatcher("NewIndex.jsp").forward(req, resp);

	}
	
	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String direccion = req.getParameter("direccion");
		String correo = req.getParameter("correo");

		Persona newUser = new Persona(id, nombre, direccion, correo);
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL);
		Response addResponse = add.request().post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
		
		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();

		System.out.println("Usuario insertado");

		resp.sendRedirect("consultar");

	}
	
	
	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String direccion = req.getParameter("direccion");
		String correo = req.getParameter("correo");

		Persona newUser = new Persona(id, nombre, direccion, correo);
		
		// ACTUALIZAMOS LA PERSONA CON UN PUT AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL);
		Response addResponse = add.request().put(Entity.entity(newUser, MediaType.APPLICATION_JSON));

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();
		
		System.out.println("Usuario actualizado");
		resp.sendRedirect("consultar");
	}
	
	private void buscar_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Client cliente = ClientBuilder.newClient();

		WebTarget target = cliente.target( URL )
				.path("/{id}")
				.resolveTemplate("id", id);

		Invocation.Builder builder = target.request( MediaType.APPLICATION_JSON ); 
		Persona persona = new Persona();
		try {
			persona = builder.get(Persona.class);

		}
		catch( NotFoundException ex ) {

			System.out.println(" Estado : " + ex.getResponse().getStatus());
			System.out.println(" Estado : " + ex.getResponse().getStatusInfo() );

		}
		
		String id1 = Integer.toString(persona.getId());
		String nombre = persona.getNombre();
		String direccion = persona.getDireccion();
		String correo = persona.getCorreo();
		
		req.setAttribute("id", id1);
		req.setAttribute("nombre", nombre);
	    System.out.println(persona.getNombre());
		req.setAttribute("direccion", direccion);
		req.setAttribute("correo", correo);
		
		req.getRequestDispatcher("actualizar.jsp").forward(req, resp);
		
		System.out.println("Usuario encontrado");

	}
	
	
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		// MANDAMOS LA NUEVA PERSONA AL SERVIDOR
		Client cliente = ClientBuilder.newClient();
		WebTarget add = cliente.target(URL)
						.path("/{id}")
						.resolveTemplate("id", id);
		Response addResponse = add.request().delete();

		String response = addResponse.readEntity(String.class);
		System.out.println(response);
		addResponse.close();
				
		System.out.println("Usuario eliminado");
		resp.sendRedirect("consultar");

	}
	
	

}
