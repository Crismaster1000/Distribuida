package com.distribuida.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.distribuida.objetos.Persona;
import com.distribuida.servicios.PersonaServ;


@WebServlet(urlPatterns = "/")
public class PersonaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	private PersonaServ servicio;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
			doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			 
				        List < Persona > persona = servicio.buscar();
				        PrintWriter pw = resp.getWriter();
				        
				        for(int i=0 ; i< persona.size(); i++) {
				        	
				        	System.out.println(persona.get(i).getId());
				        	
				        	System.out.println(persona.get(i).getNombre());
				        	
				        	System.out.println(persona.get(i).getDireccion());
				        	
				        	System.out.println(persona.get(i).getCorreo());
				        	
				        	
				        }
				        
				        req.setAttribute("listUser", persona);
				        req.getRequestDispatcher("NewIndex.jsp").forward(req, resp);
				        
						/*
						 * pw.println(" <table class=\"table table-bordered\"> ");
						 * pw.println("  <thead> "); pw.println(" <tr> "); pw.println("  <th>ID</th> ");
						 * pw.println(" <th>Nombre</th> "); pw.println(" <th>direccion</th> ");
						 * pw.println(" <th>Correo</th> "); pw.println(" </tr> ");
						 * pw.println(" </thead> "); pw.println(" <tbody> "); pw.println("   <tr> ");
						 * 
						 * for(int i=0 ; i< persona.size(); i++) {
						 * 
						 * pw.println("   <td> "); pw.println(" " +persona.get(i).getId() );
						 * pw.println("   </td> "); pw.println("   <td> "); pw.println(" "
						 * +persona.get(i).getNombre() ); pw.println("   </td> ");
						 * pw.println("   <td> "); pw.println(" " +persona.get(i).getDireccion() );
						 * pw.println("   </td> "); pw.println("   <td> "); pw.println(" "
						 * +persona.get(i).getCorreo() ); pw.println("   </td> ");
						 * pw.println("   <tr> ");
						 * 
						 * 
						 * }
						 * 
						 * pw.println(" <a id=\"regresar\" href=\"./index.jsp\">"+
						 * "		<button>Regresar</button>");
						 */
				        
				        
				        
	    }

	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String direccion = req.getParameter("direccion");
		String correo = req.getParameter("correo");

		

		Persona newUser = new Persona(id, nombre, direccion, correo);
		servicio.insertar(newUser);

		PrintWriter pw = resp.getWriter();
		pw.println("Usuario insertado ");
		System.out.println("Usuario insertado");

		resp.sendRedirect("consultar");

	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String direccion = req.getParameter("direccion");
		String correo = req.getParameter("correo");

		Persona newUser = new Persona(id, nombre, direccion, correo);
		servicio.actualizar(newUser);

		PrintWriter pw = resp.getWriter();
		pw.println("Usuario actualizado ");
		System.out.println("Usuario actualizado");
		resp.sendRedirect("consultar");
	}

	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		servicio.borrar(id);
		PrintWriter pw = resp.getWriter();
		pw.println("Usuario eliminado ");
		System.out.println("Usuario eliminado");
		resp.sendRedirect("consultar");

	}
	
	private void buscar_id(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		//List <Persona> persona = servicio.buscar_id(id);
		Persona persona = servicio.buscar_id(id);
		
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

}
