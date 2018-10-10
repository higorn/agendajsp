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

	<form method="post" action="login" class="form-signin">
	  <h3 id="_erro" style="color: red;">${erro}</h3>
	  <h2 id="_title" class="form-signin-heading">Login</h2>
	  <label for="_login" class="sr-only">Login</label>
	  <input id="_login" type="text" name="login" class="form-control" placeholder="Login" required autofocus>
	  <label for="_senha" class="sr-only">Senha</label>
	  <input id="_senha" type="password" name="senha" class="form-control" placeholder="Senha" required>
	  <button id="_submit" type="submit" class="btn btn-lg btn-primary btn-block">Fazer login</button>
	  <div>
			<a id="_cadastro" href="cadastro">Cadastrar</a>
		</div>
	</form>

</div>

<script src="static/lib/jquery/dist/jquery.min.js"></script>
<script src="static/lib/bootstrap/dist/js/bootstrap.min.js"></script>
</body>
</html>

