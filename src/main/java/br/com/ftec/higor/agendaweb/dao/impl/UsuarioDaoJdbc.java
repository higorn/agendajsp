package br.com.ftec.higor.agendaweb.dao.impl;

import br.com.ftec.higor.agendaweb.dao.UsuarioDao;
import br.com.ftec.higor.agendaweb.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author higor
 */
public class UsuarioDaoJdbc implements UsuarioDao {
  private Connection connection;

  public UsuarioDaoJdbc(Connection connection) {
	this.connection = connection;
  }


  public void inserir(Usuario usuario) throws SQLException {
    String sql = "INSERT INTO usuario(login, email, nome, senha) VALUES (?, ?, ?, ?)";
	PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setString(1, usuario.getLogin());
    pstmt.setString(2, usuario.getEmail());
    pstmt.setString(3, usuario.getNome());
    pstmt.setString(4, usuario.getSenha());
    pstmt.executeUpdate();
  }

  @Override
  public List<Usuario> recuperarTodos() throws SQLException {
    List<Usuario> resultList = new ArrayList<>();
    String sql = "SELECT * FROM usuario ORDER BY nome ASC";
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(sql);
    while (rs.next()) {
      Usuario usuario = new Usuario(rs.getString("login"),
	rs.getString("email"), rs.getString("nome"),
          rs.getString("senha"));
      resultList.add(usuario);
    }
    return resultList;
  }

  public Usuario recuperar(String login) throws SQLException {
    String sql = "SELECT * FROM usuario WHERE login = ?";
    PreparedStatement pstmt = connection.prepareStatement(sql);
    pstmt.setString(1, login);
    ResultSet rs = pstmt.executeQuery();
    if (rs.next()) {
      return new Usuario(rs.getString("login"), rs.getString("email"),
		rs.getString("nome"), rs.getString("senha"));
    }
    throw new SQLException("Impossível recuperar usuario " + login + " não encontrado.");
  }
}
