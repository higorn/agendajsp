<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="/static/lib/jquery/dist/jquery.min.js"></script>
		<script type="text/javascript" src="/static/lib/bootstrap/dist/js/bootstrap.min.js"></script>
		<link href="/static/lib/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="/static/lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  </head>
  <body>
		<div class="navbar navbar-default navbar-inverse navbar-static-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand"><span>Agenda web</span><br></a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-ex-collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" id="_usuario" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-expanded="false">${usuario}<i class="fa fa-caret-down"></i></a>
							<ul class="dropdown-menu" role="menu">
								<li>
									<a href="#" aria-expanded="false">Perfil <i class="fa fa-user"></i></a>
								</li>
								<li>
									<a href="logout">Sair <i class="fa fa-sign-out"></i></a>
								</li>
							</ul>
						</li>
					</ul>
					<div class="navbar-form navbar-right">
						<button id="_novo" type="button" data-toggle="modal" data-target="#modalNovo"
								class="btn btn-success">Nova agenda</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="modalNovo" role="dialog">
			<div class="modal-dialog">
			
			  <!-- Modal content-->
			  <div class="modal-content">
				<form class="form-horizontal" role="form" method="post" action="agendas">
					<div class="modal-header">
					  <button type="button" class="close" data-dismiss="modal">&times;</button>
					  <h4 class="modal-title">Nova agenda</h4>
					</div>
					<div class="modal-body">
						<div class="col-md-12">
							<div class="form-group">
								<div class="col-md-4">
									<label>Dia</label>
									<input type="text" class="form-control" name="dia">
								</div>
								<div class="col-md-4">
									<label>Mês</label>
									<input type="text" class="form-control" name="mes">
								</div>
								<div class="col-md-4">
									<label>Ano</label>
									<input type="text" class="form-control" name="ano">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-6">
									<label>Horário inicial</label>
									<input type="text" class="form-control" name="tempoIni">
								</div>
								<div class="col-md-6">
									<label>Horário final</label>
									<input type="text" class="form-control" name="tempoFin">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
					  <button type="submit" class="btn btn-primary">Incluir</button>
					</div>
				</form>
			  </div>
			  
			</div>
		</div>
