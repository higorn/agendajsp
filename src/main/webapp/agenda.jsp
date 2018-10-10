<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="header.jsp" %>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 id="_title">${titulo}</h1>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb">
						<li><a href="home">Home</a></li>
 						<li class="active">${titulo} - ${dia}/${mes}/${ano}</li>
 					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<form class="form-horizontal" role="form" method="post" action="agendas">
					<div class="form-group">
						<div class="col-md-4">
							<label>Horário inicial</label>
							<input type="text" class="form-control" name="tempoIni">
						</div>
						<div class="col-md-4">
							<label>Horário final</label>
							<input type="text" class="form-control" name="tempoFin">
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-4">
							<input type="hidden" name="dia" value="${dia}">
							<input type="hidden" name="mes" value="${mes}">
							<input type="hidden" name="ano" value="${ano}">
							<button type="submit" class="btn btn-primary">Iniciar agenda</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>

