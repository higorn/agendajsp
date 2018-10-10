package br.com.ftec.higor.agendaweb.dao;

import br.com.ftec.higor.agendaweb.model.Usuario;

import java.sql.SQLException;
import java.util.List;

/**
 * @author higor
 */
public interface UsuarioDao {
  void inserir(Usuario usuario) throws SQLException;
  List<Usuario> recuperarTodos() throws SQLException;
  Usuario recuperar(String login) throws SQLException;
}
