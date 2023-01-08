<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registro e Inicio de Sesion</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h2>Registrate</h2>
				
				<form:form action="/register" method="post" modelAttribute="nuevoUsuario">
					<div class="form-group">
						<form:label path="name">Nombre:</form:label>
						<form:input path="name" class="form-control"/>
						<form:errors path="name" class="text-danger"/>
					</div>
					<div class="form-group">
						<form:label path="email">Email:</form:label>
						<form:input path="email" class="form-control"/>
						<form:errors path="email" class="text-danger"/>
					</div>
					<div class="form-group">
						<form:label path="password">Password:</form:label>
						<form:password path="password" class="form-control"/>
						<form:errors path="password" class="text-danger"/>
					</div>
					<div class="form-group">
						<form:label path="confirm">Confirmar Contraseña:</form:label>
						<form:password path="confirm" class="form-control"/>
						<form:errors path="confirm" class="text-danger"/>
					</div>
					<input type="submit" value="Registrarme" class="btn btn-primary"/>
				</form:form>
			</div>
			<div class="col">
				<h2>Inicia Sesion</h2>
				
				<p class="text-danger">
					${error_login }
				</p>
				<form action="/login" method="post">
					<div class="form-control">
						<label>Email</label>
						<input type="Email" class="form-control" name="email"/>
					</div>
					<div class="form-control">
						<label>Password</label>
						<input type="password" class="form-control" name="password"/>
					</div>
					<input type="submit" value="Iniciar Sesion" class="btn btn-success"/>
				</form>
			</div>
		</div>
	</div>
</body>
</html>