package br.com.ftec.higor.agendaweb.service;

import br.com.ftec.higor.agendaweb.dao.UsuarioDao;
import br.com.ftec.higor.agendaweb.model.Usuario;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

/**
 * @author higor
 */
public class Autenticador {
  private final UsuarioDao dao;

  public Autenticador(UsuarioDao dao) {
    this.dao = dao;
  }

  public String autenticar(String login, String senha) throws LoginException {
    try {
      Usuario usuario = dao.recuperar(login);
      if (usuario.getSenha().equals(senha)) {
        return usuario.getNome();
      }
      throw new LoginException("Login invalido.");
    } catch (SQLException e) {
      e.printStackTrace();
      throw new LoginException("Login invalido.");
    }
  }
}
