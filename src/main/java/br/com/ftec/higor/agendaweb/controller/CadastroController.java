package br.com.ftec.higor.agendaweb.controller;

import br.com.ftec.higor.agendaweb.dao.UsuarioDao;
import br.com.ftec.higor.agendaweb.dao.impl.UsuarioDaoJdbc;
import br.com.ftec.higor.agendaweb.model.Usuario;
import br.com.ftec.higor.agendaweb.util.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author higor
 */
@WebServlet({"/cadastro"})
public class CadastroController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String nome = req.getParameter("nome");
    String login = req.getParameter("login");
    String email = req.getParameter("email");
    String senha = req.getParameter("senha");
    Usuario usuario = new Usuario(login, email, nome, senha);
    UsuarioDao dao = new UsuarioDaoJdbc((Connection) req.getAttribute("dbconn"));
    try {
      dao.inserir(usuario);
      resp.sendRedirect(resp.encodeRedirectURL("login"));
    } catch (SQLException e) {
      e.printStackTrace();
      req.setAttribute("erro", e.getMessage());
      req.getRequestDispatcher("cadastro.jsp").forward(req, resp);
    }
  }
}
