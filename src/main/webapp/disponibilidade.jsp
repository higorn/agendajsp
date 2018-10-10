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
 						<li><a href="agendas?dia=${agenda.dia}&mes=${agenda.mes}&ano=${agenda.ano}">
 							Agenda - ${dia}/${mes}/${ano}
 						</a></li>
 						<li class="active">${titulo}</li>
 					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="nav nav-pills">
						<li role="presentation"><a href="agendamentos">Todos</a></li>
						<li role="presentation"><a href="disponiveis">Disponíveis</a></li>
						<li role="presentation" class="active"><a href="#">Verificar disponibilidade</a></li>
					</ul>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-12">
					<ul class="list-group">
						<li class="list-group-item"><a href="disponibilidade?tipo=PRIMEIRA_CONSULTA">Primeira consulta</a></li>
						<li class="list-group-item"><a href="disponibilidade?tipo=CONSULTA">Consulta</a></li>
						<li class="list-group-item"><a href="disponibilidade?tipo=EXAME">Exames</a></li>
 					</ul>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-12">
					<ul class="list-group">
 					<c:forEach var="horario" items="${lista}">
						<li class="list-group-item">
							${horario}
						</li>
					</c:forEach>
 					</ul>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>

