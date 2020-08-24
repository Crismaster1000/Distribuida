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
				<a href="./consultar" class="navbar-brand"> CREAR CUSTOMER </a>
			</div>

		</nav>
	</header>
	<br />
	<form action="<%=request.getContextPath()%>/insertar" method="post">
		<div>
			Nombre: <input type="text" name="name">
		</div>
		<br />
		<div>
			Surname: <input type="text" name="surname" />
		</div>
		<br /> <input type="submit" value="Insertar" />
	</form>
	<br />

	<a id="Volver" href="./consultar">
		<button>Volver</button>
	</a>

</body>
</html>