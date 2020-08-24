<%@page import="javax.swing.text.html.HTMLDocument.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CRUD CUSTOMER AND ORDERS</title>
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
				<a href="./consultar" class="navbar-brand"> QUARKUS CLIENT </a>
			</div>

		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">LISTA DE ORDERS</h3>
			<hr>
                    <br>

			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Item</th>
						<th>Price</th>
						<th>Customer_Id</th>
					</tr>
				</thead>


				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="order" items="${listOrder}">
						<tr>
							<td><c:out value="${order.id}" /></td>
							<td><c:out value="${order.item}" /></td>
							<td><c:out value="${order.price}" /></td>
							<td><c:out value="${order.customer_id}" /></td>
							<td><a href="buscarOrdenID?id=<c:out value='${order.id}' />">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="borrarOrden?id=<c:out value='${order.id}' />">Borrar</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
	<div class="container text-left">
		<a href="./crearOrden.jsp" class="btn btn-success">Crear Orden</a>
	</div>
	<div class="container text-right">
		<a href="./consultar" class="btn btn-success">Volver</a>
	</div>
	<br>
	

</body>
</html>