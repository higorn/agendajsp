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
import br.com.ftec.higor.agendaweb.model.TipoAgendamento;
import br.com.ftec.higor.agendaweb.service.Agenda;
import br.com.ftec.higor.agendaweb.service.exception.HorarioAgendamentoInvalido;

/**
 * @author higor
 */
@WebServlet({ "/agendamentos" })
public class AgendamentosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("titulo", "Agendamentos");

        Agenda agenda = (Agenda) req.getSession().getAttribute("agenda");
        if (agenda == null) {
            req.setAttribute("erro", "Agenda não iniciada");
            req.getRequestDispatcher("agendamentos.jsp").forward(req, resp);
            return;
        }
        agenda.setDao(new AgendamentoDaoJdbc((Connection) req.getAttribute("dbconn")));

        listarAgendamentos(req, resp, agenda);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        Agenda agenda = (Agenda) req.getSession().getAttribute("agenda");
        if (agenda == null) {
            req.setAttribute("erro", "Agenda não iniciada");
            req.getRequestDispatcher("agendamentos.jsp").forward(req, resp);
            return;
        }
        agenda.setDao(new AgendamentoDaoJdbc((Connection) req.getAttribute("dbconn")));

        TipoAgendamento tipo = TipoAgendamento.valueOf(req.getParameter("tipo"));
        Integer hora = Integer.parseInt(req.getParameter("hora"));
        Integer min = Integer.parseInt(req.getParameter("min"));
        String nome = req.getParameter("nome");
        String telefone = req.getParameter("telefone");
        
        Agendamento agendamento = new Agendamento(agenda.getDia(), agenda.getMes(),
                agenda.getAno(), hora, min, tipo);
        agendamento.setNome(nome);
        agendamento.setTelefone(telefone);
        
        try {
            agenda.agendar(agendamento);
        } catch (Exception e) {
            if (e instanceof HorarioAgendamentoInvalido) {
                req.setAttribute("erro", "O horário informado não esta disponível para esse tiop de agendamento.");
            } else {
                req.setAttribute("erro", "Erro desconhecido.");
            }
        }
        
        listarAgendamentos(req, resp, agenda);
    }

    private void listarAgendamentos(HttpServletRequest req, HttpServletResponse resp, Agenda agenda)
            throws ServletException, IOException {

        Map<Integer, Agendamento> agendamentos = agenda.getAgendamentos();
		List<Agendamento> lista = agendamentos.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
		    .map(entry -> entry.getValue())
		    .collect(Collectors.toList());
		req.setAttribute("lista", lista);
		
        req.setAttribute("dia", agenda.getDia());
        req.setAttribute("mes", agenda.getMes());
        req.setAttribute("ano", agenda.getAno());
        req.getRequestDispatcher("agendamentos.jsp").forward(req, resp);
    }
}
