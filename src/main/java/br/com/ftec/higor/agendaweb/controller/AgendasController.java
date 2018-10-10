package br.com.ftec.higor.agendaweb.controller;

import br.com.ftec.higor.agendaweb.dao.AgendamentoDao;
import br.com.ftec.higor.agendaweb.dao.impl.AgendamentoDaoJdbc;
import br.com.ftec.higor.agendaweb.service.Agenda;

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
@WebServlet({ "/", "/home", "/agendas" })
public class AgendasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("titulo", "Agenda");
            String dia = req.getParameter("dia");
            String mes = req.getParameter("mes");
            String ano = req.getParameter("ano");

            AgendamentoDao agendamentoDao = new AgendamentoDaoJdbc((Connection) req.getAttribute("dbconn"));

            if (dia == null || mes == null || ano == null) {
                req.setAttribute("titulo", "Agendas");
                req.setAttribute("agendas", agendamentoDao.listarAgendas());
                req.getRequestDispatcher("agendas.jsp").forward(req, resp);
                return;
            }
            req.setAttribute("dia", dia);
            req.setAttribute("mes", mes);
            req.setAttribute("ano", ano);
            req.getRequestDispatcher("agenda.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("erro", e.getMessage());
            req.getRequestDispatcher("agendas.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Integer dia = Integer.parseInt(req.getParameter("dia"));
        Integer mes = Integer.parseInt(req.getParameter("mes"));
        Integer ano = Integer.parseInt(req.getParameter("ano"));

        String tempoIni = req.getParameter("tempoIni");
        String tempoFin = req.getParameter("tempoFin");
        
        Integer horaIni = Integer.parseInt(tempoIni.split(":")[0]);
        Integer minIni = Integer.parseInt(tempoIni.split(":")[1]);
        Integer horaFin = Integer.parseInt(tempoFin.split(":")[0]);
        Integer minFin = Integer.parseInt(tempoFin.split(":")[1]);

        AgendamentoDao agendamentoDao = new AgendamentoDaoJdbc((Connection) req.getAttribute("dbconn"));
        
        Agenda agenda = new Agenda(dia, mes, ano, horaIni, minIni, horaFin, minFin, agendamentoDao);
        req.getSession().setAttribute("agenda", agenda);
        
        resp.sendRedirect("agendamentos?dia=" + dia + "&mes=" + mes + "&ano=" + ano);
    }
}
