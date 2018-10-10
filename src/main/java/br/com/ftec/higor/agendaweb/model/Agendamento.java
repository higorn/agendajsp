/*
 * File:   Agendamento.java
 *
 * Created on 15/08/18, 19:35
 */
package br.com.ftec.higor.agendaweb.model;

/**
 *
 * @author higor
 */
public class Agendamento {
	private String          nome;
	private String          telefone;
	private Integer         horaInicio;
	private Integer         minutoInicio;
	private Integer         dia;
	private Integer         mes;
	private Integer         ano;
	private TipoAgendamento tipo;

	public Agendamento(Integer dia, Integer mes, Integer ano,
			Integer horaInicio, Integer minutoInicio, TipoAgendamento tipo) {
		this.horaInicio = horaInicio;
		this.minutoInicio = minutoInicio;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getMinutoInicio() {
		return minutoInicio;
	}

	public void setMinutoInicio(Integer minutoInicio) {
		this.minutoInicio = minutoInicio;
	}

	public Integer getTempo() {
		return tipo.getTempo();
	}

	public TipoAgendamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoAgendamento tipo) {
		this.tipo = tipo;
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
	
	@Override
	public String toString() {
        String hora = horaInicio < 10 ? "0" + horaInicio : horaInicio.toString();
        String min = minutoInicio < 10 ? "0" + minutoInicio : minutoInicio.toString();
        return hora + ":" + min;
	}
}
