package br.com.ftec.higor.agendaweb.dao.impl;

import br.com.ftec.higor.agendaweb.dao.AgendamentoDao;
import br.com.ftec.higor.agendaweb.dao.DaoException;
import br.com.ftec.higor.agendaweb.model.Agendamento;
import br.com.ftec.higor.agendaweb.model.TipoAgendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDaoJdbc implements AgendamentoDao {

    private Connection connection;

    public AgendamentoDaoJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Agendamento> listarAgendas() throws SQLException {
        String sql = "SELECT DISTINCT(dia), mes, ano FROM agendamento ORDER BY dia";
        List<Agendamento> resultList = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Agendamento agendamento = new Agendamento(rs.getInt("dia"), rs.getInt("mes"), rs.getInt("ano"),
                    null, null, null);
            resultList.add(agendamento);
        }
        return resultList;
    }

	@Override
	public <T extends Agendamento> void create(T agendamento) {
		String sql = "insert into agendamento(dia, mes, ano,"
				+ " hora_inicio, minuto_inicio, tipo_agendamento, nome, telefone)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
		    connection.setAutoCommit(false);
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, agendamento.getDia());
			pstmt.setInt(2, agendamento.getMes());
			pstmt.setInt(3, agendamento.getAno());
			pstmt.setInt(4, agendamento.getHoraInicio());
			pstmt.setInt(5, agendamento.getMinutoInicio());
			pstmt.setInt(6, agendamento.getTipo().ordinal());
			pstmt.setString(7, agendamento.getNome());
			pstmt.setString(8, agendamento.getTelefone());

			pstmt.execute();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public void removeAll() {
		String sql = "delete from agendamento";

		try {
		    connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Agendamento> findAll() {
		String sql = "select * from agendamento";
		List<Agendamento> agendamentos = new ArrayList<>();

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Agendamento agendamento = new Agendamento(resultSet.getInt("dia"),
						resultSet.getInt("mes"), resultSet.getInt("ano"),
						resultSet.getInt("hora_inicio"), resultSet.getInt("minuto_inicio"),
						TipoAgendamento.values()[resultSet.getInt("tipo_agendamento")]);
				agendamento.setNome(resultSet.getString("nome"));
				agendamento.setTelefone(resultSet.getString("telefone"));

				agendamentos.add(agendamento);
			}

			return agendamentos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public Agendamento findById(Integer dia, Integer mes, Integer ano, Integer horaInicio, Integer minutoInicio) {
		Agendamento agendamento = null;
		String sql = "select * from agendamento"
				+ " where dia = ? and mes = ? and ano = ?"
				+ " and hora_inicio = ? and minuto_inicio = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dia);
			pstmt.setInt(2, mes);
			pstmt.setInt(3, ano);
			pstmt.setInt(4, horaInicio);
			pstmt.setInt(5, minutoInicio);
			ResultSet resultSet = pstmt.executeQuery();

			if (resultSet.next()) {
				agendamento = new Agendamento(resultSet.getInt("dia"),
						resultSet.getInt("mes"), resultSet.getInt("ano"),
						resultSet.getInt("hora_inicio"), resultSet.getInt("minuto_inicio"),
						TipoAgendamento.values()[resultSet.getInt("tipo_agendamento")]);
				agendamento.setNome(resultSet.getString("nome"));
				agendamento.setTelefone(resultSet.getString("telefone"));
			}

			return agendamento;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public List<Agendamento> findByDiaMesAno(Integer dia, Integer mes, Integer ano) {
		List<Agendamento> agendamentos = new ArrayList<>();
		String sql = "select * from agendamento where dia = ? and mes = ? and ano = ?";

		try {
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, dia);
			pstmt.setInt(2, mes);
			pstmt.setInt(3, ano);
			ResultSet resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Agendamento agendamento = new Agendamento(resultSet.getInt("dia"),
						resultSet.getInt("mes"), resultSet.getInt("ano"),
						resultSet.getInt("hora_inicio"), resultSet.getInt("minuto_inicio"),
						TipoAgendamento.values()[resultSet.getInt("tipo_agendamento")]);
				agendamento.setNome(resultSet.getString("nome"));
				agendamento.setTelefone(resultSet.getString("telefone"));

				agendamentos.add(agendamento);
			}

			return agendamentos;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
}
