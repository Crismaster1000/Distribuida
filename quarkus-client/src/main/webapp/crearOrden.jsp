<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CREAR CUSTOMER</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: red;">
			<div>
				<a href="./consultar" class="navbar-brand"> CREAR ORDEN </a>
			</div>

		</nav>
	</header>
	<br />
	<form action="<%=request.getContextPath()%>/insertarOrden" method="post">
		<div>
			Item: <input type="text" name="item">
		</div>
		<br />
		<div>
			Price: <input type="number" name="price" />
		</div>
		<br />
		<div>
			CustomerId: <input type="number" name="customer_id" />
		</div>
		<br /> <input type="submit" value="Insertar" />
	</form>
	<br />

	<a id="Volver" href="./OrderIndex.jsp">
		<button>Volver</button>
	</a>

</body>
</html>