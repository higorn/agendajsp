package br.com.ftec.higor.agendaweb.dao;

import br.com.ftec.higor.agendaweb.model.Agendamento;

import java.sql.SQLException;
import java.util.List;

public interface AgendamentoDao {
	List<Agendamento> listarAgendas() throws SQLException;

	<T extends Agendamento> void create(T agendamento);

	void removeAll();

	List<Agendamento> findAll();

	Agendamento findById(Integer dia, Integer mes, Integer ano, Integer horaInicio, Integer minutoInicio);

	List<Agendamento> findByDiaMesAno(Integer dia, Integer mes, Integer ano);
}
