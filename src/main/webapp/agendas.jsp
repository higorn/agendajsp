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
<%-- 						<li class="active">${titulo}</li>
 --%>					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="section">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<ul class="list-group">
 					<c:forEach var="agenda" items="${agendas}">
						<li class="list-group-item">
							<a href="agendas?dia=${agenda.dia}&mes=${agenda.mes}&ano=${agenda.ano}">
								${agenda.dia}/${agenda.mes}/${agenda.ano}
							</a>
						</li>
					</c:forEach>
 					</ul>
				</div>
			</div>
		</div>
	</div>
<%@ include file="footer.jsp" %>

