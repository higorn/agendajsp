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
						<li role="presentation" class="active"><a href="#">Todos</a></li>
						<li role="presentation"><a href="disponiveis">Disponíveis</a></li>
						<li role="presentation"><a href="disponibilidade">Verificar disponibilidade</a></li>
					</ul>
				</div>
			</div>
			<div class="row" style="margin-top: 20px;">
			  <h3 id="_erro" style="color: red;">${erro}</h3>
			</div>
			<div class="row" style="margin-top: 20px;">
				<div class="col-md-12">
					<ul class="list-group">
					<c:forEach var="agendamento" items="${lista}" varStatus="loop">
						<div class="list-group-item" style="height: 55px;">
							<span style="line-height: 2.4em;">${agendamento} - ${agendamento.tipo.descricao}</span>
						<c:if test="${agendamento.tipo == 'DISPONIVEL'}">
							<button type="button" class="btn btn-primary pull-right" style="margin-left: 10px;"
									data-toggle="modal" data-target="#modal_${loop.index}">
								Agendar	
							</button>

								<!-- Modal -->
							  <div class="modal fade" id="modal_${loop.index}" role="dialog">
								<div class="modal-dialog">
								
								  <!-- Modal content-->
								  <div class="modal-content">
									<form class="form-horizontal" role="form" method="post" action="agendamentos">
										<div class="modal-header">
										  <button type="button" class="close" data-dismiss="modal">&times;</button>
										  <h4 class="modal-title">Incluir agendamento</h4>
										</div>
										<div class="modal-body">
											<div class="form-group">
												<div class="col-md-12">
													<label>Tipo de agendamento</label>
													<select name="tipo" class="form-control">
														<option value="PRIMEIRA_CONSULTA">Primeira consulta</option>
														<option value="CONSULTA">Consulta</option>
														<option value="EXAME">Exame</option>
													</select>
													<input type="hidden" name="hora" value="${agendamento.horaInicio}">
													<input type="hidden" name="min" value="${agendamento.minutoInicio}">
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-12">
													<label>Nome</label>
													<input type="text" name="nome" class="form-control">
												</div>
											</div>
											<div class="form-group">
												<div class="col-md-12">
													<label>Telefone</label>
													<input type="text" name="telefone" class="form-control">
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
						</c:if>
						<c:if test="${agendamento.tipo != 'DISPONIVEL'}">
							<button type="button" class="btn btn-info pull-right"
									data-toggle="modal" data-target="#modalView_${loop.index}">
								Visualizar	
							</button>
								<!-- Modal -->
							<div class="modal fade" id="modalView_${loop.index}" role="dialog">
								<div class="modal-dialog">
								
								  <!-- Modal content-->
								  <div class="modal-content">
									<div class="modal-header">
									  <button type="button" class="close" data-dismiss="modal">&times;</button>
									  <h4 class="modal-title">Detalhes do agendamento</h4>
									</div>
									<div class="modal-body col-md-12">
										<div class="form-group">
											<label>Tipo de agendamento</label>
											<input type="text" class="form-control" readonly
												   value="${agendamento.tipo.descricao}">
										</div>
										<div class="form-group">
											<label>Horário</label>
											<input type="text" class="form-control" readonly
												   value="${agendamento}">
										</div>
										<div class="form-group">
											<label>Nome</label>
											<input type="text" name="nome" class="form-control"
												   readonly value="${agendamento.nome}">
										</div>
										<div class="form-group">
											<label>Telefone</label>
											<input type="text" name="telefone" class="form-control"
												   readonly value="${agendamento.telefone}">
										</div>
									</div>
									<div class="modal-footer">
									  <button type="button" class="btn btn-default" data-dismiss="modal">Ok</button>
									</div>
								  </div>
								  
								</div>
							</div>
						</c:if>
						</div>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
<%@ include file="footer.jsp" %>

