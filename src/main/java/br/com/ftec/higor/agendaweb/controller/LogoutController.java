/*
 * File:   Logout.java
 *
 * Created on 18/01/17, 21:22
 */
package br.com.ftec.higor.agendaweb.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author higor
 */
@WebServlet({"/logout"})
public class LogoutController extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
  	req.getSession().removeAttribute("usuario");
    resp.sendRedirect(resp.encodeRedirectURL("login"));
  }
}
