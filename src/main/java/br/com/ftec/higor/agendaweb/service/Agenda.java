/*
 * File:   Agenda.java
 *
 * Created on 15/08/18, 19:34
 */
package br.com.ftec.higor.agendaweb.service;

import br.com.ftec.higor.agendaweb.dao.AgendamentoDao;
import br.com.ftec.higor.agendaweb.dao.impl.AgendamentoDaoJdbc;
import br.com.ftec.higor.agendaweb.model.Agendamento;
import br.com.ftec.higor.agendaweb.model.TipoAgendamento;
import br.com.ftec.higor.agendaweb.service.exception.HorarioAgendamentoInvalido;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author higor
 */
public class Agenda {

	public static final int TEMPO_PADRAO = 15;

	private final int                       timeIni;
	private final int                       timeFin;
	private       Map<Integer, Agendamento> agendamentos;
	private       AgendamentoDao            agendamentoDao;
	private       Integer                   dia;
	private       Integer                   mes;
	private       Integer                   ano;

	public Agenda(Integer dia, Integer mes, Integer ano, Integer horaIni, Integer minIni,
			Integer horaFin, Integer minFin, AgendamentoDao dao) {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		timeIni = horaIni * 60 + minIni;
		timeFin = horaFin * 60 + minFin;
		int time = timeIni;

		agendamentoDao = dao;

		agendamentos = new HashMap<>();

		while (time < timeFin) {
			int hora = time / 60;
			int min = time % 60;

			Agendamento agendamento = agendamentoDao.findById(dia, mes, ano, hora, min);
			if (agendamento == null) {
				agendamento = new Agendamento(dia, mes, ano, hora, min, TipoAgendamento.DISPONIVEL);
			}
			agendamentos.put(time, agendamento);
			time += agendamento.getTempo();
		}

	}

	public Map<Integer, Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public Map<Integer, Agendamento> getDisponiveis() {
		return agendamentos.entrySet().stream()
				.filter(entry -> entry.getValue().getTipo().equals(TipoAgendamento.DISPONIVEL))
				.collect(Collectors.toMap(e1 -> e1.getKey(), e2 -> e2.getValue()));
	}

	public Map<Integer, Agendamento> getDisponiveis(TipoAgendamento tipoAgendamento) {
		Map<Integer, Agendamento> disponiveis = getDisponiveis();
		Map<Integer, Agendamento> agendamentos = new HashMap<>();
		int time = timeIni;

		while (time < timeFin) {
			int size = tipoAgendamento.getTempo() / TEMPO_PADRAO;
			int timeSize = time + size * TEMPO_PADRAO;
			boolean invalido = false;
			for (int i = time; i < timeSize; i += TEMPO_PADRAO) {
				if (disponiveis.get(i) == null) {
					invalido = true;
					break;
				}
			}
			if (invalido) {
				time += TEMPO_PADRAO;
				continue;
			}
			agendamentos.put(time, disponiveis.get(time));
			time += TEMPO_PADRAO;
		}
		return agendamentos;
	}

	public void imprimirAgenda() {
		agendamentos.keySet().stream().sorted().forEach(key -> {
			Integer h = key / 60;
			Integer m = key % 60;
			String hora = h < 10 ? "0" + h : h.toString();
			String min = m < 10 ? "0" + m : m.toString();
			System.out.println(hora + ":" + min + " - " + agendamentos.get(key).getTipo().getDescricao());
		});
	}

	public void mostraHorariosDisponiveis() {
		getDisponiveis().entrySet().stream().map(entry -> entry.getKey()).sorted().forEach(key -> {
			Integer h = key / 60;
			Integer m = key % 60;
			String hora = h < 10 ? "0" + h : h.toString();
			String min = m < 10 ? "0" + m : m.toString();
			System.out.println(hora + ":" + min);
		});
	}

	public void mostraHorariosDisponiveis(TipoAgendamento tipoAgendamento) {
		getDisponiveis(tipoAgendamento).entrySet().stream().map(entry -> entry.getKey()).sorted().forEach(key -> {
			Integer h = key / 60;
			Integer m = key % 60;
			String hora = h < 10 ? "0" + h : h.toString();
			String min = m < 10 ? "0" + m : m.toString();
			System.out.println(hora + ":" + min);
		});
	}

	public void agendar(Agendamento agendamento) {
		Integer time = agendamento.getHoraInicio() * 60 + agendamento.getMinutoInicio();
		Map<Integer, Agendamento> disponiveis = getDisponiveis(agendamento.getTipo());
		if (disponiveis.get(time) == null) {
			throw new HorarioAgendamentoInvalido();
		}

		agendamentos.put(time, agendamento);

		int size = agendamento.getTempo() / TEMPO_PADRAO;
		int timeSize = time + size * TEMPO_PADRAO;
		for (int i = time + TEMPO_PADRAO; i < timeSize; i += TEMPO_PADRAO) {
			agendamentos.remove(i);
		}
		agendamentoDao.create(agendamento);
	}

	public Integer getDia() {
		return dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

    public void setDao(AgendamentoDao agendamentoDao) {
        this.agendamentoDao = agendamentoDao;
    }
}
