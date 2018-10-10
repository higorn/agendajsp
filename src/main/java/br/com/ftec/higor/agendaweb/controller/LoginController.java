package br.com.ftec.higor.agendaweb.controller;

import br.com.ftec.higor.agendaweb.dao.impl.UsuarioDaoJdbc;
import br.com.ftec.higor.agendaweb.service.Autenticador;

import javax.security.auth.login.LoginException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

/**
 * @author higor
 */
@WebServlet({"/login"})
public class LoginController extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String senha = req.getParameter("senha");
//    Autenticador autenticador = new Autenticador(new UsuarioDaoJdbc(JdbcUtil.getDataSource()));
	Autenticador autenticador = new Autenticador(new UsuarioDaoJdbc((Connection) req.getAttribute("dbconn")));

    try {
      String nomeUsuario = autenticador.autenticar(login, senha);
      req.getSession().setAttribute("usuario", nomeUsuario);
      resp.sendRedirect(resp.encodeRedirectURL("home"));
    } catch (LoginException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("login.jsp").forward(req, resp);
  }


}
