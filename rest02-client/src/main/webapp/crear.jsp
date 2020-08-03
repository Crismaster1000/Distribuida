<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CREAR PERSONA</title>
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
				<a href="./consultar" class="navbar-brand"> CREAR PERSONA </a>
			</div>

		</nav>
	</header>
	<br />
	<form action="<%=request.getContextPath()%>/insertar" method="post">
		<div>
			Id: <input type="number" name="id" />
		</div>
		<br />
		<div>
			Nombre: <input type="text" name="nombre">
		</div>
		<br />
		<div>
			Dirección: <input type="text" name="direccion" />
		</div>
		<br />
		<div>
			Correo: <input type="text" name="correo" />
		</div>
		<br /> <input type="submit" value="Insertar" />
	</form>
	<br />

	<a id="Volver" href="./consultar">
		<button>Volver</button>
	</a>

</body>
</html>