<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<html lang="pt-BR">
<head>
  <title>Agenda web</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">
  <link rel="stylesheet" href="static/lib/bootstrap/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="static/css/signin.css">
</head>

<body>

<div class="container">
	<h3 id="_erro">${erro}</h3>

	<form method="post" action="cadastro" class="form-signin">
	  <h2 id="_title" class="form-signin-heading">Cadastro</h2>
	  <label for="_nome" class="sr-only">Nome</label>
	  <input id="_nome" type="text" name="nome" class="form-control" placeholder="Nome" required autofocus>
	  <label for="_login" class="sr-only">Login</label>
	  <input id="_login" type="text" name="login" class="form-control" placeholder="Login" required autofocus>
	  <label for="_email" class="sr-only">Email</label>
	  <input id="_email" type="email" name="email" class="form-control" placeholder="Email" required autofocus>
	  <label for="_senha" class="sr-only">Senha</label>
	  <input id="_senha" type="password" name="senha" class="form-control" placeholder="Senha" required>
	  <button id="_submit" type="submit" class="btn btn-lg btn-primary btn-block">Cadastrar</button>
	</form>

</div>

</body>
</html>
