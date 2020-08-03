<%@page import="javax.swing.text.html.HTMLDocument.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD PERSONAS</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color:green">
			<div>
				<a href="./consultar" class="navbar-brand"> CRUD REST CLIENT </a>
			</div>

		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">LISTA DE USUARIOS</h3>
			<hr>
                    <br>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nombre</th>
						<th>Direccion</th>
						<th>Correo</th>
						<th>Acciones</th>
					</tr>
				</thead>


				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="user" items="${listUser}">
						<tr>
							<td><c:out value="${user.id}" /></td>
							<td><c:out value="${user.nombre}" /></td>
							<td><c:out value="${user.direccion}" /></td>
							<td><c:out value="${user.correo}" /></td>
							<td><a href="buscarID?id=<c:out value='${user.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="borrar?id=<c:out value='${user.id}' />">Borrar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	<div class="container text-left">
		<a href="./crear.jsp" class="btn btn-success">Crear Usuario</a>
	</div>
	<br>
	

</body>
</html>