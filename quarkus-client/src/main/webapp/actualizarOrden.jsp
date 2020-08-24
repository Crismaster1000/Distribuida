<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Order</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: green;">
			<div>
				<a href="./consultar" class="navbar-brand"> ACTUALIZAR ORDEN </a>
			</div>

		</nav>
	</header>

	<form action="<%=request.getContextPath()%>/actualizarOrden" method="post">
		<br />
		<div>
			ID: <input type="number" name="id"
				value="<%=request.getAttribute("id")%>" readonly="readonly">
		</div>
		<br />
		<div>
			Item: <input type="text" name="item"
				value="<%=request.getAttribute("item")%>">
		</div>
		<br />
		<div>
			Precio: <input type="number" name="precio"
				value="<%=request.getAttribute("precio")%>">
		</div>
		<br />
		<div>
			CustomerId: <input type="number" name="customer_id"
				value="<%=request.getAttribute("customer_id")%>" readonly="readonly">
		</div>
		<br /> <input type="submit" value="Actualizar" />
	</form>
	<br />

	<a id="Volver" href="./consultar">
		<button>Volver</button>
	</a>

</body>
</html>