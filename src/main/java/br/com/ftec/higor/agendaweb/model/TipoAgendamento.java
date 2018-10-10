package br.com.ftec.higor.agendaweb.model;/*
 * File:   TipoAgendamento.java
 *
 * Created on 20/08/18, 13:29
 */

public enum TipoAgendamento {
	DISPONIVEL(15, "Disponível"),
	PRIMEIRA_CONSULTA(45, "Primeira consulta"),
	CONSULTA(30, "Consulta"),
	EXAME(15, "Exames");

	private Integer tempo;
	private String  descricao;

	TipoAgendamento(Integer tempo, String descricao) {
		this.tempo = tempo;
		this.descricao = descricao;
	}

	public Integer getTempo() {
		return tempo;
	}

	public String getDescricao() {
		return descricao;
	}
}
