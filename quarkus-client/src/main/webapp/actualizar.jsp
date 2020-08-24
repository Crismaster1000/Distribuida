<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Customer</title>
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
				<a href="./consultar" class="navbar-brand"> ACTUALIZAR CUSTOMER </a>
			</div>

		</nav>
	</header>

	<form action="<%=request.getContextPath()%>/actualizar" method="post">
		<br />
		<div>
			ID: <input type="number" name="id"
				value="<%=request.getAttribute("id")%>" readonly="readonly">
		</div>
		<br />
		<div>
			Nombre: <input type="text" name="name"
				value="<%=request.getAttribute("name")%>">
		</div>
		<br />
		<div>
			Surname: <input type="text" name="surname"
				value="<%=request.getAttribute("surname")%>">
		</div>
		<br /> <input type="submit" value="Actualizar" />
	</form>
	<br />

	<a id="Volver" href="./consultar">
		<button>Volver</button>
	</a>

</body>
</html>