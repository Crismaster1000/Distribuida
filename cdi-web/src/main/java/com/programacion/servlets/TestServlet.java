package com.programacion.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.programacion.servicios.ServicioOperaciones;

@WebServlet(urlPatterns = "/test")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// Colocamos la dependencia mediante Inject
	@Inject
	private ServicioOperaciones servicio;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// recibimos dos parametros
		String n1 = req.getParameter("num1");
		String n2 = req.getParameter("num2");
		// convertirmos a enteros
		int v1 = Integer.valueOf(n1);
		int v2 = Integer.valueOf(n2);

		int respuesta = servicio.sumar(v1, v2);

		PrintWriter pw = resp.getWriter();

		pw.println("Funcionando !!");
		pw.println(respuesta);

		pw.println("<html>");
		pw.println("<body>");

		pw.println("<b>Respuesta: </b>" + respuesta);

		pw.println("<body>");
		pw.println("</html>");

		System.out.println(servicio);

	}

}
