package br.com.ftec.higor.agendaweb.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ftec.higor.agendaweb.dao.impl.AgendamentoDaoJdbc;
import br.com.ftec.higor.agendaweb.model.Agendamento;
import br.com.ftec.higor.agendaweb.service.Agenda;

/**
 * @author higor
 */
@WebServlet({ "/disponiveis" })
public class DisponiveisController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("titulo", "Disponíveis");
        Agenda agenda = (Agenda) req.getSession().getAttribute("agenda");
        if (agenda == null) {
            req.setAttribute("erro", "Agenda não iniciada");
            req.getRequestDispatcher("disponiveis.jsp").forward(req, resp);
            return;
        }
        agenda.setDao(new AgendamentoDaoJdbc((Connection) req.getAttribute("dbconn")));

        Map<Integer, Agendamento> agendamentos = agenda.getDisponiveis();
		List<Agendamento> lista = agendamentos.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
		    .map(entry -> entry.getValue())
		    .collect(Collectors.toList());
		req.setAttribute("lista", lista);
		
        req.setAttribute("dia", agenda.getDia());
        req.setAttribute("mes", agenda.getMes());
        req.setAttribute("ano", agenda.getAno());
        req.getRequestDispatcher("disponiveis.jsp").forward(req, resp);
    }
}
